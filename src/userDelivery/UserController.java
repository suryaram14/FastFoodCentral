package userDelivery;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import connection.MySqlConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class UserController implements Initializable{
	
	@FXML
    private AnchorPane UserPane;
	
	@FXML
    private TextField txt_name;

    @FXML
    private TextField txt_phoneNumber;

    @FXML
    private TextArea txt_address;

    @FXML
    private TextArea txt_notes;
	
    Connection conn = null;
    PreparedStatement ps = null;
    
    @FXML
    public void confirmOrder() {
    	conn = MySqlConnection.ConnectDb();
    	String delivery = "insert into delivery(name, phone_number, address, notes) values(?,?,?,?)";
    	if (txt_name.getText().equals("") || txt_phoneNumber.getText().equals("") || txt_address.getText().equals("")) {  
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setContentText("Fields cannot be empty.");
			alertError.showAndWait();
    	}
    	else {
    		try {
    			ps = conn.prepareStatement(delivery);
    			ps.setString(1, txt_name.getText());
    			ps.setString(2, txt_phoneNumber.getText());
    			ps.setString(3, txt_address.getText());
    			ps.setString(4, txt_notes.getText());
    			ps.execute();

    			Alert success = new Alert(AlertType.ERROR);
    			success.setContentText("Order confirmed");
    			success.showAndWait();
    			
    			AnchorPane pane = FXMLLoader.load(getClass().getResource("/login/Login.FXML"));
    			UserPane.getChildren().setAll(pane);
    			}
    		catch(Exception e) {
    			Alert alertError = new Alert(AlertType.ERROR);
    			alertError.setContentText("Delivery failed");
    			alertError.showAndWait();
    		}
    	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
