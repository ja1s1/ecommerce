package com.ecommerce.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommerce.config.DatabaseConnection;
import com.ecommerce.model.client.Client;

public class ClientRepository {
    public record compteConnexion(Integer id, String mdpHash) {}

    public boolean inserer(Client client, String email, String hash) throws SQLException {
        String sql = """
            INSERT INTO client (email, mot_de_passe_hash, nom, prenom, telephone)
            VALUES (?, ?, ?, ?, ?)
            ON CONFLICT (lower(email)) DO NOTHING
            """;
        
        try (Connection connexion = DatabaseConnection.getConnection();
            PreparedStatement ps = connexion.prepareStatement(sql)){
                ps.setString(1, email);
                ps.setString(2, hash);
                ps.setString(3, client.getNom());
                ps.setString(4, client.getPrenom());
                ps.setString(5, client.getTelephone());

                return ps.executeUpdate() == 1;

            }
    }

    public compteConnexion trouverParEmail(String email) throws SQLException{
        String sql = """
            SELECT id, mot_de_passe_hash
            FROM client
            WHERE lower(email) = lower(?)
            """;

            try (Connection connexion = DatabaseConnection.getConnection();
                PreparedStatement ps = connexion.prepareStatement(sql)){
                    ps.setString(1, email);

                    try (ResultSet rs = ps.executeQuery()){
                        if (!rs.next()) {
                            return null;
                        }

                        return new compteConnexion(rs.getInt("id"), rs.getString("mot_de_passe_hash"));
                    }
                }
    }
}
