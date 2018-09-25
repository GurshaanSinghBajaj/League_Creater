package application;

import javafx.scene.control.TextField;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
			if(loginmodel.isLogin(username_field.getText(), password_field.getText())) 
				login_response.setText("CORRECT USERNAME AND PASSWORD");
			else
				login_response.setText("WRONG USERNAME OR PASSWORD");
			} catch(SQLException e) {
				e.printStackTrace();
		}
	}
}
