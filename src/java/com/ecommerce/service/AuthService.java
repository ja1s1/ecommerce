package com.ecommerce.service;

import org.mindrot.jbcrypt.BCrypt;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Locale;

import com.ecommerce.model.client.Client;
import com.ecommerce.repository.ClientRepository;

public class AuthService {
    private final ClientRepository clientRepository = new ClientRepository();

    public boolean register(Client client) throws SQLException {
        if (client.getEmail() == null || client.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("L'adresse email est obligatoire");
        }

        if (client.getMdp() == null || client.getMdp().trim().isEmpty()) {
            throw new IllegalArgumentException("le mot de passe est obligatoire");
        }

        if (client.getNom() == null || client.getNom().trim().isEmpty()
            || client.getPrenom() == null || client.getPrenom().trim().isEmpty()
            || client.getTelephone() == null || client.getTelephone().trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Tout les infos doit être complété");
        }

        if (client.getMdp().getBytes(StandardCharsets.UTF_8).length > 72) {
            throw new IllegalArgumentException("Le mot de passe dépasse la limite de 72 octets de BCrypt");
        }

        String email = client.getEmail().trim().toLowerCase(Locale.ROOT);
        String hash = BCrypt.hashpw(client.getMdp(), BCrypt.gensalt(12));

        return clientRepository.inserer(client, email, hash);
    }

    public Integer login (String email, String mdp) throws SQLException {
        if (email == null || mdp == null || mdp.isEmpty() || mdp.getBytes(StandardCharsets.UTF_8).length > 72) {
            return null;
        }

        ClientRepository.compteConnexion compte = clientRepository.trouverParEmail(email.trim());

        if (compte == null || BCrypt.checkpw(mdp, compte.mdpHash())) {
            return null;
        }

        return compte.id();
    }
}
