package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	
    Connection conn = null;
    Statement statement = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    double totalPrice = 0.0;
    
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
    	Burger burger = new Burger();
    	EventHandler<ActionEvent> eventClassic = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	burger.setId(4);
            	burger.setSelection("Classic Burger");
            	burger.setPrice(10.99);
                txt_cart.appendText(burger.getSelection() + " $" + burger.getPrice() + "\n"); 
                totalPrice += burger.getPrice();
                writeBurgerOrder(burger);
            } 
        }; 
        classicB_button.setOnAction(eventClassic);
        
        
        EventHandler<ActionEvent> eventChicken = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	burger.setId(5);
            	burger.setSelection("Chicken Burger");
            	burger.setPrice(11.99);
                txt_cart.appendText(burger.getSelection() + " $" + burger.getPrice() + "\n"); 
                totalPrice += burger.getPrice();
                writeBurgerOrder(burger);
            } 
        }; 
        chickenB_button.setOnAction(eventChicken);
        
        
        EventHandler<ActionEvent> eventVeggie = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	burger.setId(6);
            	burger.setSelection("Veggie Burger");
            	burger.setPrice(9.99);
                txt_cart.appendText(burger.getSelection() + "$" + burger.getPrice() + "\n"); 
                totalPrice += burger.getPrice();
                writeBurgerOrder(burger);
            } 
        }; 
        vegB_button.setOnAction(eventVeggie); 
    }

    
    @FXML
    public void orderDrinks() {
    	conn = MySqlConnection.ConnectDb();
    	Drinks drinks = new Drinks();
    	EventHandler<ActionEvent> eventVanilla = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	drinks.setId(7);
            	drinks.setSelection("Vanilla Milkshake");
            	drinks.setPrice(3.99);
                txt_cart.appendText(drinks.getSelection() + " $" + drinks.getPrice() + "\n"); 
                totalPrice += drinks.getPrice();
                writeDrinksOrder(drinks);
            } 
        }; 
        vanilla_button.setOnAction(eventVanilla);
        
        
        EventHandler<ActionEvent> eventStrawberry = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	drinks.setId(8);
            	drinks.setSelection("Strawberry Milkshake");
            	drinks.setPrice(3.99);
                txt_cart.appendText(drinks.getSelection() + " $" + drinks.getPrice() + "\n"); 
                totalPrice += drinks.getPrice();
                writeDrinksOrder(drinks);
            } 
        }; 
        strawberry_button.setOnAction(eventStrawberry);
        
        
        EventHandler<ActionEvent> eventChocolate = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	drinks.setId(9);
            	drinks.setSelection("Chocolate Milkshake");
            	drinks.setPrice(3.99);
                txt_cart.appendText(drinks.getSelection() + " $" + drinks.getPrice() + "\n"); 
                totalPrice += drinks.getPrice();
                writeDrinksOrder(drinks);
            } 
        }; 
        chocolate_button.setOnAction(eventChocolate);
    }

    
    @FXML
    public void orderPizza() {
    	conn = MySqlConnection.ConnectDb();
    	Pizza pizza = new Pizza();
    	EventHandler<ActionEvent> eventMeat = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	pizza.setId(1);
            	pizza.setSelection("Meat Lovers Pizza");
            	pizza.setPrice(9.99);
                txt_cart.appendText(pizza.getSelection() + " $" + pizza.getPrice() + "\n");
                totalPrice += pizza.getPrice();
                writePizzaOrder(pizza);
            } 
        }; 
        meatP_button.setOnAction(eventMeat);
        
        
        EventHandler<ActionEvent> eventPepperoni = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	pizza.setId(2);
            	pizza.setSelection("Pepperoni pizza");
            	pizza.setPrice(8.99);
                txt_cart.appendText(pizza.getSelection() + " $" + pizza.getPrice() + "\n");
                totalPrice += pizza.getPrice();
                writePizzaOrder(pizza);
            } 
        }; 
        pepperoniP_button.setOnAction(eventPepperoni);
        
        
        EventHandler<ActionEvent> eventVeggie = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	pizza.setId(3);
            	pizza.setSelection("Veggie Pizza");
            	pizza.setPrice(7.99);
                txt_cart.appendText(pizza.getSelection() + " $" + pizza.getPrice() + "\n"); 
                totalPrice += pizza.getPrice();
                writePizzaOrder(pizza);
            } 
        }; 
        vegP_button.setOnAction(eventVeggie);   
    }

    
    public void writePizzaOrder(Pizza pizza) {
    	try {
    		conn = MySqlConnection.ConnectDb();
    		statement = conn.createStatement();
    		String write = "insert into food(item_name, price) " +  "values (" + pizza.getId() + ", " + pizza.getPrice() + ")";
    		System.out.println(write);
    		statement.executeUpdate(write);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    }
    
    
    public void writeBurgerOrder(Burger burger) {
    	try {
    		conn = MySqlConnection.ConnectDb();
    		statement = conn.createStatement();
    		String write = "insert into food(item_name, price) " +  "values (" + burger.getId() + ", " + burger.getPrice() + ")";
    		System.out.println(write);
    		statement.executeUpdate(write);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    }

    
    public void writeDrinksOrder(Drinks drinks) {
    	try {
    		conn = MySqlConnection.ConnectDb();
    		statement = conn.createStatement();
    		String write = "insert into food(item_name, price) " +  "values (" + drinks.getId() + ", " + drinks.getPrice() + ")";
    		System.out.println(write);
    		statement.executeUpdate(write);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}   	
    }

    @FXML
    public void readOrder() {
    	try {
    		conn = MySqlConnection.ConnectDb();
    		String read = "select * from food";
    		ps = conn.prepareStatement(read);
    		rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			int order_id = rs.getInt(1);
    			int item_name = rs.getInt(2);
    			double price = rs.getDouble(3);
    		}
    		String s = String.valueOf(totalPrice);
			total.appendText(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    @FXML 
    public void clear() {
    	Alert success = new Alert(AlertType.CONFIRMATION);
    	success.setContentText("Items deleted");
    	success.showAndWait();
    	totalPrice = 0.0;
    	txt_cart.clear();
    	total.clear();
    }
    
    @FXML
    public void confirmOrder() throws IOException {	
		if(total.getText().equals("")) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText("Total not calculated");
	    	error.showAndWait();
		}
		else {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/userDelivery/User.FXML"));
			homePane.getChildren().setAll(pane);
			txt_cart.clear();
			total.clear();
		}
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
