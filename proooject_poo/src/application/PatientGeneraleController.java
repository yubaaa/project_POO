package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientGeneraleController implements Initializable {

	
	 @FXML
	    private Button AjouterButton;

	    @FXML
	    private Button ModifierButton;

	    @FXML
	    private Button SupprimerButton;

	    @FXML
	    private Button close;

	    @FXML
	    private Button logout;

	    @FXML
	    private AnchorPane main_form;

	    @FXML
	    private Button minimize;

	    @FXML
	    private Button backButton;
	    
    private double x = 0;
    private double y = 0;

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        // Hide the current window
        ((Button) event.getSource()).getScene().getWindow().hide();
        // Load the new FXML file
        Parent root = FXMLLoader.load(getClass().getResource("Patient.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        // Hide the current window
        ((Button) event.getSource()).getScene().getWindow().hide();
        // Load the new FXML file
        Parent root = FXMLLoader.load(getClass().getResource("PatientModifier.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void Supprimer(ActionEvent event) throws IOException {
        // Hide the current window
        ((Button) event.getSource()).getScene().getWindow().hide();
        // Load the new FXML file
        Parent root = FXMLLoader.load(getClass().getResource("PatientSupprimer.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void afficher(ActionEvent event) throws IOException {
        // Hide the current window
        ((Button) event.getSource()).getScene().getWindow().hide();
        // Load the new FXML file
        Parent root = FXMLLoader.load(getClass().getResource("afficher.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    
    
    public void logout() {

        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                // HIDE THE DASHBOARD FORM
                logout.getScene().getWindow().hide();
                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
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
    
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    
    @FXML
    private void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
