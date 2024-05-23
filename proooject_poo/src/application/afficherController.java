
package application ;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

public class afficherController extends Application {
	
    @FXML
    private Button backButton;


    private TableView<Utilisateur> table;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tableau des Utilisateurs");

        table = new TableView<>();
        TableColumn<Utilisateur, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Utilisateur, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Utilisateur, String> prenomColumn = new TableColumn<>("Prénom");
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Utilisateur, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().addAll(idColumn, nomColumn, prenomColumn, emailColumn);

        VBox vbox = new VBox(table);
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();

        loadData();
    }
    
    private double x = 0;
    private double y = 0;
    public void back() {
 	   
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

    private void loadData() {
        try {
            List<Utilisateur> utilisateurs = DatabaseUtil.getUtilisateurs();
            ObservableList<Utilisateur> data = FXCollections.observableArrayList(utilisateurs);
            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}