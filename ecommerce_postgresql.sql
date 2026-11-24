-- Boutique mono-vendeur. PostgreSQL 12+. Installer une seule fois dans une base vide.
-- Le backend applique les regles ci-dessous avant d'enregistrer les donnees.
-- Client et catalogue : normaliser le courriel, refuser le courriel vide et les
-- categories cycliques. Verifier prix >= 0 et position des images >= 0.
-- Panier : un seul proprietaire (client ou invite) et quantites positives.
-- Promotion : code normalise en majuscules et non vide, valeur > 0, fin apres
-- debut si renseignee. Type montant ou pourcentage, taux au plus egal a 100.
-- Commande : frais >= 0. Repartir la remise entre les lignes, arrondir et
-- enregistrer la remise finale de chaque ligne entre zero et son montant brut.
-- Stock : 0 <= reserve <= physique. Quantites allouees positives ; quantites
-- expediees et liberees positives ou nulles, leur somme au plus egale a l'allocation.
-- Expedition et retour : quantites positives et livraison apres ou a l'expedition.
-- Paiements et remboursements : montants positifs. Valider les moyens de paiement,
-- les usages d'adresse, les etats et les transitions dans les services concernes.
-- A chaque changement de statut, ecrire l'historique dans la meme transaction.
BEGIN;
CREATE SCHEMA boutique;
SET LOCAL search_path TO boutique, public;

CREATE TABLE client (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email text NOT NULL,
    mot_de_passe_hash text NOT NULL,
    nom text NOT NULL,
    prenom text,
    telephone text,
    cree_le timestamptz NOT NULL DEFAULT now()
);
CREATE UNIQUE INDEX client_email_unique ON client (lower(email));

CREATE TABLE adresse_client (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    client_id bigint NOT NULL REFERENCES client ON DELETE CASCADE,
    destinataire text NOT NULL,
    rue text NOT NULL,
    ville text NOT NULL,
    code_postal text,
    pays char(2) NOT NULL,
    telephone text
);

CREATE TABLE categorie (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    parent_id bigint REFERENCES categorie,
    nom text NOT NULL,
    slug text NOT NULL UNIQUE
);
CREATE TABLE produit (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nom text NOT NULL,
    slug text NOT NULL UNIQUE,
    description text,
    prix_base_ttc numeric(14,2) NOT NULL,
    actif boolean NOT NULL DEFAULT true
);
CREATE TABLE produit_categorie (
    produit_id bigint REFERENCES produit ON DELETE CASCADE,
    categorie_id bigint REFERENCES categorie ON DELETE CASCADE,
    PRIMARY KEY (produit_id, categorie_id)
);
CREATE INDEX categorie_produits ON produit_categorie(categorie_id);
CREATE TABLE variante (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    produit_id bigint NOT NULL REFERENCES produit,
    sku text NOT NULL UNIQUE,
    couleur text NOT NULL DEFAULT '',
    taille text NOT NULL DEFAULT '',
    prix_ttc numeric(14,2),
    actif boolean NOT NULL DEFAULT true,
    UNIQUE (produit_id, couleur, taille)
);
CREATE TABLE image_produit (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    produit_id bigint NOT NULL REFERENCES produit ON DELETE CASCADE,
    url text NOT NULL,
    position integer NOT NULL,
    UNIQUE (produit_id, position)
);

CREATE TABLE depot (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nom text NOT NULL UNIQUE
);
CREATE TABLE stock (
    variante_id bigint REFERENCES variante,
    depot_id bigint REFERENCES depot,
    physique integer NOT NULL DEFAULT 0,
    reserve integer NOT NULL DEFAULT 0,
    PRIMARY KEY (variante_id, depot_id)
);

CREATE TABLE panier (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    client_id bigint REFERENCES client,
    jeton_invite_hash text UNIQUE,
    etat text NOT NULL DEFAULT 'ouvert',
    cree_le timestamptz NOT NULL DEFAULT now()
);
CREATE UNIQUE INDEX panier_client_ouvert ON panier(client_id) WHERE etat = 'ouvert';
CREATE TABLE ligne_panier (
    panier_id bigint REFERENCES panier ON DELETE CASCADE,
    variante_id bigint REFERENCES variante,
    quantite integer NOT NULL,
    PRIMARY KEY (panier_id, variante_id)
);

CREATE TABLE promotion (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code text NOT NULL UNIQUE,
    type_remise text NOT NULL,
    valeur numeric(14,2) NOT NULL,
    debut timestamptz NOT NULL,
    fin timestamptz
);
CREATE TABLE commande (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    numero text NOT NULL UNIQUE,
    panier_id bigint UNIQUE REFERENCES panier,
    client_id bigint REFERENCES client,
    email_contact text NOT NULL,
    statut text NOT NULL DEFAULT 'en_attente',
    promotion_id bigint REFERENCES promotion,
    frais_livraison numeric(14,2) NOT NULL DEFAULT 0,
    cree_le timestamptz NOT NULL DEFAULT now()
);
CREATE INDEX commande_par_client ON commande(client_id, cree_le DESC);
CREATE TABLE adresse_commande (
    commande_id bigint REFERENCES commande,
    usage text,
    destinataire text NOT NULL,
    rue text NOT NULL,
    ville text NOT NULL,
    code_postal text,
    pays char(2) NOT NULL,
    telephone text,
    PRIMARY KEY (commande_id, usage)
);
CREATE TABLE ligne_commande (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    commande_id bigint NOT NULL REFERENCES commande,
    variante_id bigint NOT NULL REFERENCES variante,
    sku_achete text NOT NULL,
    designation text NOT NULL,
    quantite integer NOT NULL,
    prix_unitaire_ttc numeric(14,2) NOT NULL,
    remise_ligne numeric(14,2) NOT NULL DEFAULT 0,

    UNIQUE (commande_id, variante_id),
    UNIQUE (id, variante_id),
    UNIQUE (id, commande_id)
);

-- Une allocation par ligne et depot. Le service met a jour stock et allocation ensemble.
CREATE TABLE allocation_stock (
    ligne_id bigint NOT NULL,
    variante_id bigint NOT NULL,
    depot_id bigint NOT NULL,
    quantite integer NOT NULL,
    expedie integer NOT NULL DEFAULT 0,
    libere integer NOT NULL DEFAULT 0,
    FOREIGN KEY (ligne_id, variante_id) REFERENCES ligne_commande(id, variante_id),
    FOREIGN KEY (variante_id, depot_id) REFERENCES stock,
    PRIMARY KEY (ligne_id, depot_id)
);
CREATE TABLE paiement (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    commande_id bigint NOT NULL REFERENCES commande,
    moyen text NOT NULL,
    prestataire text NOT NULL,
    reference_externe text,
    cle_idempotence text NOT NULL UNIQUE,
    montant numeric(14,2) NOT NULL,
    statut text NOT NULL DEFAULT 'en_attente',
    cree_le timestamptz NOT NULL DEFAULT now(),
    UNIQUE (prestataire, reference_externe)
);
CREATE INDEX paiement_commande ON paiement(commande_id);
CREATE TABLE remboursement (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    paiement_id bigint NOT NULL REFERENCES paiement,
    cle_idempotence text NOT NULL UNIQUE,
    montant numeric(14,2) NOT NULL,
    motif text,
    statut text NOT NULL,
    cree_le timestamptz NOT NULL DEFAULT now()
);
CREATE TABLE expedition (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    commande_id bigint NOT NULL REFERENCES commande,
    depot_id bigint NOT NULL REFERENCES depot,
    transporteur text NOT NULL,
    numero_suivi text,
    expedie_le timestamptz,
    livre_le timestamptz,

    UNIQUE (transporteur, numero_suivi),
    UNIQUE (id, commande_id, depot_id)
);
CREATE TABLE ligne_expedition (
    expedition_id bigint NOT NULL,
    commande_id bigint NOT NULL,
    depot_id bigint NOT NULL,
    ligne_id bigint NOT NULL,
    quantite integer NOT NULL,
    PRIMARY KEY (expedition_id, ligne_id),
    FOREIGN KEY (expedition_id, commande_id, depot_id) REFERENCES expedition(id, commande_id, depot_id),
    FOREIGN KEY (ligne_id, commande_id) REFERENCES ligne_commande(id, commande_id),
    FOREIGN KEY (ligne_id, depot_id) REFERENCES allocation_stock
);
CREATE TABLE retour (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    expedition_id bigint NOT NULL,
    ligne_id bigint NOT NULL,
    quantite integer NOT NULL,
    motif text NOT NULL,
    statut text NOT NULL,
    cree_le timestamptz NOT NULL DEFAULT now(),
    FOREIGN KEY (expedition_id, ligne_id) REFERENCES ligne_expedition
);

CREATE TABLE historique_commande (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    commande_id bigint NOT NULL REFERENCES commande,
    statut text NOT NULL,
    enregistre_le timestamptz NOT NULL DEFAULT clock_timestamp()
);
COMMIT;
