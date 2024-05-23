package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/votre_base_de_donnees";
    private static final String USER = "votre_utilisateur";
    private static final String PASSWORD = "votre_mot_de_passe";

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static ObservableList<Map<String, Object>> getPatients() throws Exception {
        ObservableList<Map<String, Object>> data = FXCollections.observableArrayList();
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Patient");

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("id_patient", rs.getInt("id_patient"));
            row.put("nom", rs.getString("nom"));
            row.put("prenom", rs.getString("prenom"));
            row.put("date_naissance", rs.getDate("date_naissance"));
            data.add(row);
        }

        rs.close();
        stmt.close();
        conn.close();

        return data;
    }
}