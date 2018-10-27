package application;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class loginController implements Initializable{
	
	public loginModel loginmodel = new loginModel();
	@FXML
	private Label isConnected;
	@FXML
	private Label login_response;
	@FXML
	private TextField username_field;
	@FXML
	private TextField password_field;
	@FXML
	private Button login_button;
	
	@FXML
	public void Login (ActionEvent event) {
		try {
			if(loginmodel.isLogin(username_field.getText(), password_field.getText())) {
				login_response.setText("CORRECT USERNAME AND PASSWORD");
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root=loader.load(getClass().getResource("/application/Main_menu.fxml").openStream());
				mainmenu_controller userController = (mainmenu_controller)loader.getController();
				System.out.println("Hello\n");
				userController.GetUser(username_field.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				Stage stage = (Stage) login_button.getScene().getWindow();
			    stage.close();
			}
			else
				login_response.setText("WRONG USERNAME OR PASSWORD");
			} catch(SQLException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(loginmodel.isDbConnected()) {
			isConnected.setText("LEAGUE MANAGER");
		}
		else
			isConnected.setText("Not Connected");
	}
	
}
