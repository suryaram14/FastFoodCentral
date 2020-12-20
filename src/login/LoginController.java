package login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connection.MySqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{
	@FXML
    private AnchorPane loginPane;
	
	@FXML
    private TextField txt_username;

	@FXML
    private PasswordField txt_password;

    @FXML
    private Button login_button;

    @FXML
    private Button signUp_button;
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public void login(ActionEvent event) throws Exception{
    	conn = MySqlConnection.ConnectDb();
    	String loginSQL = "select * from users where username = ? and password = ?";
    	if (txt_username.getText().equals("") && txt_password.getText().equals("")) {  
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setContentText("Fields cannot be empty.");
			alertError.showAndWait();
    	}
    	else {
    		try {
    			ps = conn.prepareStatement(loginSQL);
    			ps.setString(1, txt_username.getText());
    			ps.setString(2, txt_password.getText());
    			rs = ps.executeQuery();

    			if(rs.next()) {
    				Parent pane = FXMLLoader.load(getClass().getResource("/application/Main.FXML"));
    				Scene scene = new Scene(pane);
    				Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    				stage.setScene(scene);
    				stage.setHeight(518);
    				stage.setWidth(946);
    				stage.show();
    			}
    			
    			else {
    				Alert alertError = new Alert(AlertType.ERROR);
        			alertError.setContentText("Username and/or password is wrong");
        			alertError.showAndWait();
    			}
    		}
    		catch(Exception e) {
    			Alert alertError = new Alert(AlertType.ERROR);
    			alertError.setContentText("Username or password is wrong");
    			alertError.showAndWait();
    			System.out.println(e.getMessage());
    		}
    	}
    }
    
    public void goToSignUp(ActionEvent event) throws Exception{
    	try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/signUp/SignUp.FXML"));
			loginPane.getChildren().setAll(pane);
		} catch (IOException error) {
			error.printStackTrace();
		}
    }
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
