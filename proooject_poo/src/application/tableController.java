/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author WINDOWS 10
 */
public class tableController implements Initializable {


   
	 @FXML
	    private Button backButton;
	 
    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button addMed_btn;

    @FXML
    private Button pruchase_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane addMedicines_form;

    @FXML
    private TextField addMedicines_medicineID;
    
    @FXML
    private TextField addMedecin_role;

    @FXML
    private TextField addMedicines_brand;

    @FXML
    private TextField addMedicines_productName;
    

    @FXML
    private TextField addMedicines_nom;

    @FXML
    private TextField addMedicines_numtel;

    @FXML
    private TextField addMedicines_prenom;


    @FXML
    private TextField addMedicines_email;

    @FXML
    private TextField addMedicines_sexe;
    
    @FXML
    private DatePicker addMedicines_date;
    
    @FXML
    private DatePicker addUser_dateNaissance;

    @FXML
    private TextField addUser_email;

    @FXML
    private TextField addUser_id;

    @FXML
    private TextField addUser_nom;

    @FXML
    private TextField addUser_numTel;

    @FXML
    private TextField addUser_prenom;

    @FXML
    private TextField addUser_sexe;

	
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


    @FXML
    private ComboBox<?> addMedicines_type;

    @FXML
    private ComboBox<?> addMedicines_status;

    @FXML
    private TextField addMedicines_price;

    @FXML
    private ImageView addMedicines_imageView;

    @FXML
    private Button addMedicines_importBtn;

    @FXML
    private Button addMedicines_addBtn;

    @FXML
    private Button addMedicines_updateBtn;

    @FXML
    private Button addMedicines_clearBtn;

    @FXML
    private Button addMedicines_deleteBtn;

    @FXML
    private TextField addMedicines_search;

    @FXML
    private TableView<medicineData> addMedicines_tableView;
    
    @FXML
    private TableColumn<medicineData, String> addMedicines_col_medicineID;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_brand;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_productName;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_type;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_price;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_status;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_date;



//    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private Image image;
    
    
    
   
  
    
    
    
  
    public void addMedicinesAdd(){

        String sql = "INSERT INTO Utilisateur (id, nom, prenom, num_tel, email, date_de_naissance, role, sexe) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {

            Alert alert;

            if (addUser_id.getText().isEmpty()
                    || addUser_nom.getText().isEmpty()
                    || addUser_prenom.getText().isEmpty()
                    || addUser_numTel.getText().isEmpty()
                    || addUser_email.getText().isEmpty()
                    || addUser_dateNaissance.getValue() == null
                //    || addUser_role.getSelectionModel().getSelectedItem() == null
                    || addUser_sexe.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // CHECK IF THE USER ID YOU WANT TO INSERT EXIST
                String checkData = "SELECT id FROM Utilisateur WHERE id = '"
                        + addUser_id.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User ID: " + addUser_id.getText() + " already exists!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setInt(1, Integer.parseInt(addUser_id.getText()));
                    prepare.setString(2, addUser_nom.getText());
                    prepare.setString(3, addUser_prenom.getText());
                    prepare.setDouble(4, Double.parseDouble(addUser_numTel.getText()));
                    prepare.setString(5, addUser_email.getText());

                    LocalDate date = addUser_dateNaissance.getValue();
                    java.sql.Date sqlDate = java.sql.Date.valueOf(date);

                    prepare.setDate(6, sqlDate);
                    prepare.setString(7, "médecin"); // Directly setting the role to "médecin"
                    prepare.setString(8, addUser_sexe.getText());

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                  //  addUserShowListData();
                //    addUserReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void addMedicineUpdate(){
        
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");
        
        String sql = "UPDATE medicine SET brand = '"
                +addMedicines_brand.getText()+"', productName = '"
                +addMedicines_productName.getText()+"', type = '"
                +addMedicines_type.getSelectionModel().getSelectedItem()+"', status = '"
                +addMedicines_status.getSelectionModel().getSelectedItem()+"', price = '"
                +addMedicines_price.getText()+"', image = '"+uri+"' WHERE medicine_id = '"
                +addMedicines_medicineID.getText()+"'";
        
        connect = database.connectDb();
        
        try{
            Alert alert;
            
            if(addMedicines_medicineID.getText().isEmpty()
                    || addMedicines_brand.getText().isEmpty()
                    || addMedicines_productName.getText().isEmpty()
                    || addMedicines_type.getSelectionModel().getSelectedItem() == null
                    || addMedicines_status.getSelectionModel().getSelectedItem() == null
                    || addMedicines_price.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Medicine ID:" + addMedicines_medicineID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    
                    addMedicineShowListData();
                    addMedicineReset();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void addMedicineDelete(){
        
        String sql = "DELETE FROM medicine WHERE medicine_id = '"+addMedicines_medicineID.getText()+"'";
        
        connect = database.connectDb();
        
        try{
            Alert alert;
            
            if(addMedicines_medicineID.getText().isEmpty()
                    || addMedicines_brand.getText().isEmpty()
                    || addMedicines_productName.getText().isEmpty()
                    || addMedicines_type.getSelectionModel().getSelectedItem() == null
                    || addMedicines_status.getSelectionModel().getSelectedItem() == null
                    || addMedicines_price.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Medicine ID:" + addMedicines_medicineID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    
                    addMedicineShowListData();
                    addMedicineReset();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void addMedicineReset(){
        addMedicines_medicineID.setText("");
        addMedicines_brand.setText("");
        addMedicines_productName.setText("");
        addMedicines_price.setText("");
        addMedicines_type.getSelectionModel().clearSelection();
        addMedicines_status.getSelectionModel().clearSelection();
        
        addMedicines_imageView.setImage(null);
        
        getData.path = "";
        
    }
    
    private String[] addMedicineListT = {"Hydrocodone", "Antibiotics", "Metformin", "Losartan", "Albuterol"};
    public void addMedicineListType(){
        List<String> listT = new ArrayList<>();
        
        for(String data: addMedicineListT){
            listT.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(listT);
        addMedicines_type.setItems(listData);
        
    }
    
    
    
    private String[] addMedicineStatus = {"Available", "Not Available"};
    public void addMedicineListStatus(){
        List<String> listS = new ArrayList<>();
        
        for(String data: addMedicineStatus){
            listS.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(listS);
        addMedicines_status.setItems(listData);
    }
    
    public void addMedicineImportImage(){
        
        FileChooser open = new FileChooser();
        open.setTitle("Import Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));
        
        File file = open.showOpenDialog(main_form.getScene().getWindow());
        
        if(file != null){
            image = new Image(file.toURI().toString(), 118, 147, false, true);
            
            addMedicines_imageView.setImage(image);
            
            getData.path = file.getAbsolutePath();
        }
        
    }
    
    public ObservableList<medicineData> addMedicinesListData(){
        
        String sql = "SELECT * FROM medicine";
        
        ObservableList<medicineData> listData = FXCollections.observableArrayList();
        
        connect = database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            medicineData medData;
            
            while(result.next()){
                medData = new medicineData(result.getInt("medicine_id"), result.getString("brand")
                        , result.getString("productName"), result.getString("type")
                        , result.getString("status"), result.getDouble("price")
                        , result.getString("image"), result.getDate("date"));
                
                listData.add(medData);
            }
            
        }catch(Exception e){e.printStackTrace();}
        return listData;
    }
    
    private ObservableList<medicineData> addMedicineList;
    public void addMedicineShowListData(){
        addMedicineList = addMedicinesListData();
        
        addMedicines_col_medicineID.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        addMedicines_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        addMedicines_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        addMedicines_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        addMedicines_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        addMedicines_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        addMedicines_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        addMedicines_tableView.setItems(addMedicineList);
        
    }
    
    public void addMedicineSearch(){
        
        FilteredList<medicineData> filter = new FilteredList<>(addMedicineList, e-> true);
        
        addMedicines_search.textProperty().addListener((Observable, oldValue, newValue) ->{
            
            filter.setPredicate(predicateMedicineData ->{
                
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String searchKey = newValue.toLowerCase();
                
                if(predicateMedicineData.getMedicineId().toString().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getBrand().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getProductName().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getType().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getStatus().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getPrice().toString().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getDate().toString().contains(searchKey)){
                    return true;
                }else return false;
            });
        });
        
        SortedList<medicineData> sortList = new SortedList<>(filter);
        
        sortList.comparatorProperty().bind(addMedicines_tableView.comparatorProperty());
        addMedicines_tableView.setItems(sortList);
        
    }
    
    public void addMedicineSelect(){
        medicineData medData = addMedicines_tableView.getSelectionModel().getSelectedItem();
        int num = addMedicines_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < - 1){return;}
        
        addMedicines_medicineID.setText(String.valueOf(medData.getMedicineId()));
        addMedicines_brand.setText(medData.getBrand());
        addMedicines_productName.setText(medData.getProductName());
        addMedicines_price.setText(String.valueOf(medData.getPrice()));
        
        String uri = "file:" + medData.getImage();
        
        image = new Image(uri, 118, 147, false, true);
        addMedicines_imageView.setImage(image);
        
        getData.path = medData.getImage();
        
    }
    
    private double totalP;
    private double totalPriceD;
    
    private double balance;
    private double amount;
    
    private SpinnerValueFactory<Integer> spinner;
    
    public void defaultNav(){
        dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
    }
    
   
    
    public void displayUsername(){
    //    String user = getData.username;
        String user = "pffffff";

                        // BIG LETTER THE FIRST LETTER THEN THE REST ARE SMALL LETTER
        username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
        
    }
    
    
    public void ajoutermedecin() throws SQLException {
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
    public int Supprimermedecin(int id) throws SQLException {
        PreparedStatement preStat;
        String query;
        Connection connect;
        
        connect = database.connectDb();
        
        try {
            connect.setAutoCommit(false);
            
            // Supprimer les rendez-vous du patient
            query = "DELETE FROM RendezVous WHERE patient_id = ?";
            preStat = connect.prepareStatement(query);
            preStat.setInt(1, id);
            preStat.executeUpdate();
            
            // Supprimer les antécédents du patient
            query = "DELETE FROM antecedents WHERE patient_id = ?";
            preStat = connect.prepareStatement(query);
            preStat.setInt(1, id);
            preStat.executeUpdate();
            
            // Supprimer les allergies du patient
            query = "DELETE FROM allergies WHERE patient_id = ?";
            preStat = connect.prepareStatement(query);
            preStat.setInt(1, id);
            preStat.executeUpdate();
            
            // Supprimer les traitements du patient
            query = "DELETE FROM traitements WHERE patient_id = ?";
            preStat = connect.prepareStatement(query);
            preStat.setInt(1, id);
            preStat.executeUpdate();
            
            // Supprimer le patient
            query = "DELETE FROM patient WHERE user_id = ?";
            preStat = connect.prepareStatement(query);
            preStat.setInt(1, id);
            int s1 = preStat.executeUpdate();
            
            // Supprimer l'utilisateur
            query = "DELETE FROM Utilisateur WHERE id = ?";
            preStat = connect.prepareStatement(query);
            preStat.setInt(1, id);
            int s2 = preStat.executeUpdate();
            
            connect.commit();
            
            if (s1 > 0 && s2 > 0) {
                return 1;
            }
            return 0;
            
        } catch (SQLException e) {
            connect.rollback();
            throw e;
        } finally {
            connect.setAutoCommit(true);
            connect.close();
        }
    }



    public void modifiermedecin() throws SQLException {
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

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        
    }

}