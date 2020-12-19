package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.util.ResourceBundle;

import connection.MySqlConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable{
	
	@FXML
    private AnchorPane homePane;

    @FXML
    private Button meatP_button;

    @FXML
    private Button pepperoniP_button;

    @FXML
    private Button vegP_button;

    @FXML
    private Button classicB_button;

    @FXML
    private Button chickenB_button;

    @FXML
    private Button vegB_button;

    @FXML
    private Button vanilla_button;

    @FXML
    private Button strawberry_button;

    @FXML
    private Button chocolate_button;

    @FXML
    private Button logout_button;
    
    @FXML
    private TextArea txt_cart;
    
    @FXML
    private TextField total;
    
    
    double price = 0;
    Connection conn = null;
    
    @FXML
    public void logout() {
    	try {
			Stage mainStage = (Stage) homePane.getScene().getWindow();
			mainStage.close();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/Login.FXML"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane, 700, 400);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    @FXML
    public void orderBurger() {
    	conn = MySqlConnection.ConnectDb();
    	Burger burger = new Burger(price);
    	EventHandler<ActionEvent> eventClassic = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	burger.setPrice(10.99);
                txt_cart.appendText("Classic Burger " + "$" + burger.getPrice() + "\n"); 
            } 
        }; 
        classicB_button.setOnAction(eventClassic);
        
        
        EventHandler<ActionEvent> eventChicken = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	burger.setPrice(11.99);
                txt_cart.appendText("Chicken Burger " + "$" + burger.getPrice() + "\n"); 
            } 
        }; 
        chickenB_button.setOnAction(eventChicken);
        
        
        EventHandler<ActionEvent> eventVeggie = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	burger.setPrice(9.99);
                txt_cart.appendText("Veggie Burger " + "$" + burger.getPrice() + "\n"); 
            } 
        }; 
        vegB_button.setOnAction(eventVeggie);
        
    }

    
    @FXML
    public void orderDrinks() {
    	conn = MySqlConnection.ConnectDb();
    	Drinks drinks = new Drinks(price);
    	EventHandler<ActionEvent> eventVanilla = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	drinks.setPrice(3.99);
                txt_cart.appendText("Vanilla Milkshake " + "$" + drinks.getPrice() + "\n"); 
            } 
        }; 
        vanilla_button.setOnAction(eventVanilla);
        
        
        EventHandler<ActionEvent> eventStrawberry = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	drinks.setPrice(3.99);
                txt_cart.appendText("Strawberry Milkshake " + "$" + drinks.getPrice() + "\n"); 
            } 
        }; 
        strawberry_button.setOnAction(eventStrawberry);
        
        
        EventHandler<ActionEvent> eventChocolate = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	drinks.setPrice(3.99);
                txt_cart.appendText("Chocolate Milkshake " + "$" + drinks.getPrice() + "\n"); 
            } 
        }; 
        chocolate_button.setOnAction(eventChocolate);
    }

    
    @FXML
    public void orderPizza() {
    	conn = MySqlConnection.ConnectDb();
    	Pizza pizza = new Pizza(price);
    	EventHandler<ActionEvent> eventMeat = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	pizza.setPrice(9.99);
                txt_cart.appendText("Meat Lovers Pizza " + "$" + pizza.getPrice() + "\n"); 
            } 
        }; 
        meatP_button.setOnAction(eventMeat);
        
        
        EventHandler<ActionEvent> eventPepperoni = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	pizza.setPrice(8.99);
                txt_cart.appendText("Pepperoni pizza " + "$" + pizza.getPrice() + "\n"); 
            } 
        }; 
        pepperoniP_button.setOnAction(eventPepperoni);
        
        
        EventHandler<ActionEvent> eventVeggie = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	pizza.setPrice(7.99);
                txt_cart.appendText("Veggie Pizza " + "$" + pizza.getPrice() + "\n"); 
            } 
        }; 
        vegP_button.setOnAction(eventVeggie);
    }


    
    @FXML
    public void confirmOrder() {
    	Alert success = new Alert(AlertType.CONFIRMATION);
    	success.setContentText("Order confirmed");
    	success.showAndWait();
    	txt_cart.clear();
    }
    
    @FXML
    public void clear() {
    	Alert success = new Alert(AlertType.CONFIRMATION);
    	success.setContentText("Items deleted");
    	success.showAndWait();
    	txt_cart.clear();
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
