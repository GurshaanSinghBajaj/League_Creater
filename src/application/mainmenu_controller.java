package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class mainmenu_controller implements Initializable{

	String x;
	
	@FXML
	private Label hello_text;
	@FXML
	private Label main_menu_label;
	@FXML
	private Button league_button;
	@FXML
	private Button team_button;
	@FXML
	private Button player_button;
	@FXML
	private Button fixture_button;
	@FXML
	private Button logout_button;
	
	public void GetUser(String user) {
		hello_text.setText("Hello " + user);
		x=user;
	}
		
	@FXML
	public void league(ActionEvent event) {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = null;
		try {
			root = loader.load(getClass().getResource("/application/user_window.fxml").openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		user_windowController userController = (user_windowController)loader.getController();
		userController.GetUser(x);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Stage stage = (Stage) league_button.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void team(ActionEvent event) {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = null;
		try {
			root = loader.load(getClass().getResource("/application/user_window2.fxml").openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		user_windowController2 userController = (user_windowController2)loader.getController();
		userController.GetUser(x);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Stage stage = (Stage) team_button.getScene().getWindow();
	    stage.close();
	}
	
	/*public void league(ActionEvent event) {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = null;
		try {
			root = loader.load(getClass().getResource("/application/user_window.fxml").openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		user_windowController userController = (user_windowController)loader.getController();
		userController.GetUser(x);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Stage stage = (Stage) league_button.getScene().getWindow();
	    stage.close();
	}
	
	public void league(ActionEvent event) {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = null;
		try {
			root = loader.load(getClass().getResource("/application/user_window.fxml").openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		user_windowController userController = (user_windowController)loader.getController();
		userController.GetUser(x);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Stage stage = (Stage) league_button.getScene().getWindow();
	    stage.close();
	}*/
	
	@FXML
	public void logout(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = null;
		root = loader.load(getClass().getResource("/application/login.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Stage stage = (Stage) logout_button.getScene().getWindow();
	    stage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
