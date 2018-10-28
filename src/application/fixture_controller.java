package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import application.fixture_detail;
import application.fixture_model;

public class fixture_controller implements Initializable {
	
	String x;

	@FXML
	private Label user_label;
	@FXML
	private Label user_text;
	@FXML
    private TableView<fixture_detail> Fixture_Table;
    @FXML
    private TableColumn<fixture_detail, String>  fixture_ID;
    @FXML
    private TableColumn<fixture_detail, String>  hometeam;
    @FXML
    private TableColumn<fixture_detail, String> awayteam;
    @FXML
    private TableColumn<fixture_detail, String> date;
    @FXML
    private TableColumn<fixture_detail, String> time;
    @FXML
    private TableColumn<fixture_detail, String> venue;
    @FXML
    private TextField enter_fixtureid;
    @FXML
    private TextField enter_hometeam;
    @FXML
    private TextField enter_awayteam;
    @FXML
    private TextField enter_date;
    @FXML
    private TextField enter_time;
    @FXML
    private TextField enter_venue;
    @FXML
    private Button add_button;
    @FXML
    private Button delete_button;
    @FXML
    private TextField league_id;
    @FXML
    private Button search_button;
    @FXML
    private Button fixture_mainmenu; 
    
    @FXML
    private void search() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<fixture_detail> Fixture_List = fixture_model.fixture_table(x);
            Fixture_Table.setItems(Fixture_List);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    @FXML
    private void searchLeague() throws SQLException, ClassNotFoundException {
        try {
        	String check = league_id.getText();
        	if(check.length()>0) {
        		System.out.println("calling");
        		ObservableList<fixture_detail> Fixture_List = fixture_model.search_fixture(league_id.getText(),x);
        		Fixture_Table.setItems(Fixture_List);
        	}
        	else 
        	{
        		System.out.println("Calling Search");
        		search();
        	}
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    @FXML
    private void insertFixture(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    	try {
    		fixture_model.insertfixture(enter_fixtureid.getText(),enter_hometeam.getText(),enter_awayteam.getText(),
				enter_date.getText(),enter_time.getText(),enter_venue.getText(),league_id.getText(),x);
    	} catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    	try {
			search();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    private void DeleteFixture(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    	try {
    		fixture_model.deletefixture(enter_fixtureid.getText(),x);
    	} catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    	try {
			search();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    private void backtomenu(ActionEvent event) {
    	Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = null;
		try {
			root = loader.load(getClass().getResource("/application/Main_menu.fxml").openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainmenu_controller userController = (mainmenu_controller)loader.getController();
		System.out.println("Hello\n");
		userController.GetUser(x);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Stage stage = (Stage) fixture_mainmenu.getScene().getWindow();
	    stage.close();
    }
    
    public void GetUser(String user) {
		user_label.setText("Hello " + user);
		x=user;
		try {
			search();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    fixture_ID.setCellValueFactory(cellData -> cellData.getValue().fixture_idProperty());
        hometeam.setCellValueFactory(cellData -> cellData.getValue().home_teamProperty());
        awayteam.setCellValueFactory(cellData -> cellData.getValue().away_teamProperty());
        date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        venue.setCellValueFactory(cellData -> cellData.getValue().venueProperty());
	}
}
