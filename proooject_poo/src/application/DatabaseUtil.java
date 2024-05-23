package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/votre_base_de_donnees";
    private static final String USER = "votre_utilisateur";
    private static final String PASSWORD = "votre_mot_de_passe";

    public static List<Utilisateur> getUtilisateurs() throws Exception {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Utilisateur");

        while (rs.next()) {
            Utilisateur utilisateur = new Utilisateur(
                rs.getInt("id"),
                rs.getString("role"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("num_tel"),
                rs.getString("email"),
                rs.getDate("date_de_naissance"),
                rs.getString("sexe")
            );
            utilisateurs.add(utilisateur);
        }
        return utilisateurs;
    }
}