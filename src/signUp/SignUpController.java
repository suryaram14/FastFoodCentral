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
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;


public class SignUpController implements Initializable{
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
    
    @FXML
    private ImageView imgMovie;
    
    Connection conn = null;
    PreparedStatement ps = null;
    
    public void handle(ActionEvent event) throws Exception {
		if (event.getSource() == login_button) {
			goToLogin();
		} else {
			signUp(event);
		}
	}
    
    public void signUp(ActionEvent event) throws Exception{
    	conn = MySqlConnection.ConnectDb();
    	String signUpSQL = "insert into users(username, password) values(?, ?)";
    	if (txt_username.getText().equals("") && txt_password.getText().equals("")) {  
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
