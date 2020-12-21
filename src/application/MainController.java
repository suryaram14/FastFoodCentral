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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable{
	// variables initialized in fxml file
	// variables and onAction can be found in Main.fxml file
	
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

	// variables to connect with back end database to store and retrieve info
    Connection conn = null;
    Statement statement = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    // variable initialized to get total price of items ordered
    double totalPrice = 0.0;
    
    
    // method for user to logout of account after ordering food
    // Once logout button is pressed on top right
    // controller reroutes user to the Login.FXML file where 
    // they would have to login again to order food
    @FXML
    public void logout() {
    	try {
			Stage mainStage = (Stage) homePane.getScene().getWindow();
			mainStage.close();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/Login.FXML"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane, 663, 473);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // method for user to order burger
    // Used tab pane to separate burgers, pizza, and drinks
    // connection to database
    // create a new object of Burger
    // set Id, selection name, and price of each burger
    // EventHandler initialized to each of the 3 options provided - one for classic, chicken, and veggie
    // every time 'place order' is clicked on, cart is updated and order is written to mysql database depending on user selection
    
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

    
    // method for user to order drinks
    // Used tab pane to separate burgers, pizza, and drinks
    // connection to database
    // create a new object of Drinks
    // set Id, selection name, and price of each drink
    // EventHandler initialized to each of the 3 options provided - one for vanilla, strawberry, and chocolate
    // every time 'place order' is clicked on, cart is updated and order is written to mysql database depending on user selection
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

    
    // method for user to order pizza
    // Used tab pane to separate burgers, pizza, and drinks
    // connection to database
    // create a new object of Pizza
    // set Id, selection name, and price of each pizza
    // EventHandler initialized to each of the 3 options provided - one for meat lovers, pepperoni, and veggie
    // every time 'place order' is clicked on, cart is updated and order is written to mysql database depending on user selection
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

    
    // helper method used in orderPizza()
    // method connects to database
    // sql statement to update 'food' database based on the pizza item selected
    // database stores orders for users
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
    
    
    // helper method used in orderBurger()
    // method connects to database
    // sql statement to update 'food' database based on the burger item selected
    // database stores orders per each user
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

    
    // helper method used in orderDrinks()
    // method connects to database
    // sql statement to update 'food' database based on the drink item selected
    // database stores orders per each user
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

    
    // method to read orders database
    // method writeBurgerOrder(), writeDrinksOrder(), writePizzaOrder() all inputs data into 'food' table
    // this method connects to the database and reads the order placed
    // once read, the cart updates and users can see what they ordered in the cart tab
    // total price text field also given for users to see their total amount they need to pay
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
    
    
    // method for users to clear their cart if they want to delete their order
    // if cart is empty, error message pops up
    // else all the parameters (total price, cart, total) are deleted and reset
    @FXML 
    public void clear() {
    	if(txt_cart.getText().equals("")) {
    		Alert error = new Alert(AlertType.ERROR);
			error.setContentText("No items added to cart");
	    	error.showAndWait();
    	}
    	else {
    		Alert success = new Alert(AlertType.CONFIRMATION);
    		success.setContentText("Items deleted");
    		success.showAndWait();
    		totalPrice = 0.0;
    		txt_cart.clear();
    		total.clear();
    	}
    }
    
    
    // method for users to proceed to input detials once done with order
    // if total price is not calculated or nothing is added into cart, error messages pop up
    // else, user is redirected to User.FXML page where they can input their name, phone number, address, and any notes they have
    // cart, total, and total price clears every time an order is successful
    @FXML
    public void proceed(ActionEvent event) throws IOException {	
		if(total.getText().equals("")) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText("Total not calculated");
	    	error.showAndWait();
		}
		else if(totalPrice == 0.0) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText("No items selected");
	    	error.showAndWait();
		}
		else {
			Parent pane = FXMLLoader.load(getClass().getResource("/userDelivery/User.FXML"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setHeight(422);
			stage.setWidth(629);
			stage.show();
			totalPrice = 0.0;
			txt_cart.clear();
			total.clear();
		}
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
