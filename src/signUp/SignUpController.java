package signUp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import connection.MySqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;


public class SignUpController implements Initializable{
	// variables initialized in fxml file
	// variables and onAction can be found in SignUp.fxml file
	@FXML
    private AnchorPane signUpPane;
	
	@FXML
    private TextField txt_username;

	@FXML
    private PasswordField txt_password;

    @FXML
    private Button login_button;

    @FXML
    private Button signUp_button;
    
    // variables to connect with back end database to store and retrieve info
    Connection conn = null;
    PreparedStatement ps = null;
    
    
    // method to check if users want to sign up or login
    // if login_button is pressed, users rerouted to Login.FXML
    // else, users sign up based on the info they input
    public void handle(ActionEvent event) throws Exception {
		if (event.getSource() == login_button) {
			goToLogin();
		} else {
			signUp(event);
		}
	}
    
    
    // connection to database
    // sql statement to insert details users provided into 'users' table
    // if fields are left empty and user presses 'sign up', error message pops up
    // else, user types in their preferred username and password and database stores the details provided in 'users' table
    // once user signs up, they are rerouted to Login.FXML page
    // if user signs up with a username already in the database, error message pops up which tells user to choose another username
    public void signUp(ActionEvent event) throws Exception{
    	conn = MySqlConnection.ConnectDb();
    	String signUpSQL = "insert into users(username, password) values(?, ?)";
    	if (txt_username.getText().equals("") || txt_password.getText().equals("")) {  
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setContentText("Fields cannot be empty.");
			alertError.showAndWait();
    	}
    	else {
    		try {
    			ps = conn.prepareStatement(signUpSQL);
    			ps.setString(1, txt_username.getText());
    			ps.setString(2, txt_password.getText());
    			ps.execute();

    			AnchorPane pane = FXMLLoader.load(getClass().getResource("/login/Login.FXML"));
    			signUpPane.getChildren().setAll(pane);	
    		}
    		catch(Exception e) {
    			Alert alertError = new Alert(AlertType.ERROR);
    			alertError.setContentText("Username already exists. Please choose another username.");
    			alertError.showAndWait();
    		}
    	}
    }
    
    
    // If user has already signed up previously, user can click on 'login_button'
    // once the button has been pressed, user is rerouted to Login.FXML page where they can enter their credentials
    public void goToLogin(){
    	try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/login/Login.FXML"));
			signUpPane.getChildren().setAll(pane);
		} catch (IOException error) {
			error.printStackTrace();
		}
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
