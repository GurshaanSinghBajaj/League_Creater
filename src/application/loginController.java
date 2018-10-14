package application;

import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private ImageView imageview;
	
	@FXML
	private TextField username_field;
	
	@FXML
	private TextField password_field;
	
	@FXML
	private Button login_button;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(loginmodel.isDbConnected()) {
			isConnected.setText("LEAGUE CREATER");
			 File file = new File("1_17.png");
		     Image image = new Image(file.toURI().toString());
		     imageview = new ImageView(image);
		}
		else
			isConnected.setText("Not Connected");
	}
	
	public void Login (ActionEvent event) {
		try {
			if(loginmodel.isLogin(username_field.getText(), password_field.getText())) {
				login_response.setText("CORRECT USERNAME AND PASSWORD");
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root=loader.load(getClass().getResource("/application/user_window.fxml").openStream());
				user_windowController userController = (user_windowController)loader.getController();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
