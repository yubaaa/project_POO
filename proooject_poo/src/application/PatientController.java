package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PatientController implements Initializable  {
	
    @FXML
    private Button backButton;
	
	@FXML
    private TextField date;

    @FXML
    private TextField email;

    @FXML
    private Button eng;

    @FXML
    private TextField id;

    @FXML
    private Button mod;

    @FXML
    private TextField nom;

    @FXML
    private TextField num_tel;

    @FXML
    private TextField prenom;

    @FXML
    private  TextField role;

    @FXML
    private TextField sexe;

    @FXML
    private Button sup;
    
    public void ajouter() throws SQLException {
        Connection connect = null;
        PreparedStatement prepare = null;

        try {
            String query = "INSERT INTO Utilisateur(id, role, nom, prenom, num_tel, email, date_de_naissance, sexe) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            connect = database.connectDb();
            prepare = connect.prepareStatement(query);

            prepare.setInt(1, Integer.parseInt(id.getText()));
            prepare.setString(2, role.getText());
            prepare.setString(3, nom.getText());
            prepare.setString(4, prenom.getText());
            prepare.setString(5, num_tel.getText());
            prepare.setString(6, email.getText());
            prepare.setDate(7, java.sql.Date.valueOf(date.getText())); // assuming date is in YYYY-MM-DD format
            prepare.setString(8, sexe.getText());

            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
   
        }
    }  
    
    @FXML
    public void modifier() throws SQLException {
        PreparedStatement prepare = null;
        Connection connect = null;

        try {
            String query = "UPDATE Utilisateur SET nom = ?, prenom = ?, num_tel = ?, email = ?, date_de_naissance = ?, sexe = ? WHERE nom = ? AND prenom = ?";
            connect = database.connectDb();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, nom.getText());
            prepare.setString(2, prenom.getText());
            prepare.setString(3, num_tel.getText());
            prepare.setString(4, email.getText());
            prepare.setDate(5, java.sql.Date.valueOf(date.getText()));
            prepare.setString(6, sexe.getText());
            prepare.setString(7, nom.getText());
            prepare.setString(8, prenom.getText());

            int rowsUpdated = prepare.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Patient updated successfully!");
            } else {
                System.out.println("No patient found with the given name and surname.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prepare != null) prepare.close();
            if (connect != null) connect.close();
        }
    }
 
  
    @FXML
    public void supprimer() throws SQLException {
        PreparedStatement preStat = null;
        Connection connect = null;

        try {
            connect = database.connectDb();
            connect.setAutoCommit(false);

            String query = "DELETE FROM RendezVous WHERE patient_id = (SELECT id FROM patient WHERE user_id = (SELECT id FROM Utilisateur WHERE nom = ? AND prenom = ?))";
            preStat = connect.prepareStatement(query);
            preStat.setString(1, nom.getText());
            preStat.setString(2, prenom.getText());
            preStat.executeUpdate();

            query = "DELETE FROM antecedents WHERE patient_id = (SELECT id FROM patient WHERE user_id = (SELECT id FROM Utilisateur WHERE nom = ? AND prenom = ?))";
            preStat = connect.prepareStatement(query);
            preStat.setString(1, nom.getText());
            preStat.setString(2, prenom.getText());
            preStat.executeUpdate();

            query = "DELETE FROM allergies WHERE patient_id = (SELECT id FROM patient WHERE user_id = (SELECT id FROM Utilisateur WHERE nom = ? AND prenom = ?))";
            preStat = connect.prepareStatement(query);
            preStat.setString(1, nom.getText());
            preStat.setString(2, prenom.getText());
            preStat.executeUpdate();

            query = "DELETE FROM traitements WHERE patient_id = (SELECT id FROM patient WHERE user_id = (SELECT id FROM Utilisateur WHERE nom = ? AND prenom = ?))";
            preStat = connect.prepareStatement(query);
            preStat.setString(1, nom.getText());
            preStat.setString(2, prenom.getText());
            preStat.executeUpdate();

            query = "DELETE FROM patient WHERE user_id = (SELECT id FROM Utilisateur WHERE nom = ? AND prenom = ?)";
            preStat = connect.prepareStatement(query);
            preStat.setString(1, nom.getText());
            preStat.setString(2, prenom.getText());
            preStat.executeUpdate();

            query = "DELETE FROM Utilisateur WHERE nom = ? AND prenom = ?";
            preStat = connect.prepareStatement(query);
            preStat.setString(1, nom.getText());
            preStat.setString(2, prenom.getText());
            preStat.executeUpdate();

            connect.commit();

        } catch (SQLException e) {
            if (connect != null) connect.rollback();
            e.printStackTrace();
        } finally {
            if (preStat != null) preStat.close();
            if (connect != null) {
                connect.setAutoCommit(true);
                connect.close();
            }
        }
    }



    public void modifierPatient() throws SQLException {
        PreparedStatement prepare = null;
        Connection connect = null;

        try {
            String query = "UPDATE Utilisateur SET nom = ?, prenom = ?, num_tel = ?, email = ?, date_de_naissance = ?, sexe = ? WHERE id = ?";
            connect = database.connectDb();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, nom.getText());
            prepare.setString(2, prenom.getText());
            prepare.setString(3, num_tel.getText());
            prepare.setString(4, email.getText());
            prepare.setDate(5, java.sql.Date.valueOf(date.getText())); 
            prepare.setString(6, sexe.getText());
            prepare.setInt(7, Integer.parseInt(id.getText()));

            int rowsUpdated = prepare.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Patient updated successfully!");
            } else {
                System.out.println("No patient found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Close resources
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
    }
    
    
    private double x = 0;
    private double y = 0;
    public void back() {
 	   
    	try {
    	backButton.getScene().getWindow().hide();
    	  // LINK YOUR DASHBOARD FORM
        Parent root = FXMLLoader.load(getClass().getResource("menuAya.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });
        
        stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.setScene(scene);
        stage.show();
        
    
     }catch(Exception e){e.printStackTrace();}
    }
    
    public void back2() {
  	   
    	try {
    	backButton.getScene().getWindow().hide();
    	  // LINK YOUR DASHBOARD FORM
        Parent root = FXMLLoader.load(getClass().getResource("PatientGenerale.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });
        
        stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.setScene(scene);
        stage.show();
        
    
     }catch(Exception e){e.printStackTrace();}
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
