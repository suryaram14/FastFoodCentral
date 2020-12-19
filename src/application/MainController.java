package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

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
            	burger.setSelection(1);
            	burger.setPrice(10.99);
                txt_cart.appendText("Classic Burger" + " $" + burger.getPrice() + "\n"); 
                totalPrice += burger.getPrice();
                writeBurgerOrder(burger);
            } 
        }; 
        classicB_button.setOnAction(eventClassic);
        
        
        EventHandler<ActionEvent> eventChicken = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	burger.setSelection(2);
            	burger.setPrice(11.99);
                txt_cart.appendText("Chicken Burger" + " $" + burger.getPrice() + "\n"); 
                totalPrice += burger.getPrice();
                writeBurgerOrder(burger);
            } 
        }; 
        chickenB_button.setOnAction(eventChicken);
        
        
        EventHandler<ActionEvent> eventVeggie = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	burger.setSelection(3);
            	burger.setPrice(9.99);
                txt_cart.appendText("Veggie Burger" + "$" + burger.getPrice() + "\n"); 
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
            	drinks.setSelection(4);
            	drinks.setPrice(3.99);
                txt_cart.appendText("Vanilla Milkshake" + " $" + drinks.getPrice() + "\n"); 
                totalPrice += drinks.getPrice();
                writeDrinksOrder(drinks);
            } 
        }; 
        vanilla_button.setOnAction(eventVanilla);
        
        
        EventHandler<ActionEvent> eventStrawberry = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	drinks.setSelection(5);
            	drinks.setPrice(3.99);
                txt_cart.appendText("Strawberry Milkshake" + " $" + drinks.getPrice() + "\n"); 
                totalPrice += drinks.getPrice();
                writeDrinksOrder(drinks);
            } 
        }; 
        strawberry_button.setOnAction(eventStrawberry);
        
        
        EventHandler<ActionEvent> eventChocolate = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	drinks.setSelection(6);
            	drinks.setPrice(3.99);
                txt_cart.appendText("Chocolate Milkshake"+ " $" + drinks.getPrice() + "\n"); 
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
            	pizza.setSelection(7);
            	pizza.setPrice(9.99);
                txt_cart.appendText("Meat Lovers Pizza" + " $" + pizza.getPrice() + "\n");
                totalPrice += pizza.getPrice();
                writePizzaOrder(pizza);
            } 
        }; 
        meatP_button.setOnAction(eventMeat);
        
        
        EventHandler<ActionEvent> eventPepperoni = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	pizza.setSelection(8);
            	pizza.setPrice(8.99);
                txt_cart.appendText("Pepperoni pizza" + " $" + pizza.getPrice() + "\n");
                totalPrice += pizza.getPrice();
                writePizzaOrder(pizza);
            } 
        }; 
        pepperoniP_button.setOnAction(eventPepperoni);
        
        
        EventHandler<ActionEvent> eventVeggie = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	pizza.setSelection(9);
            	pizza.setPrice(7.99);
                txt_cart.appendText("Veggie Pizza" + " $" + pizza.getPrice() + "\n"); 
                totalPrice += pizza.getPrice();
                writePizzaOrder(pizza);
            } 
        }; 
        vegP_button.setOnAction(eventVeggie);   
    }


    public void writePizzaOrder(Pizza pizza) {	
    	conn = MySqlConnection.ConnectDb();
    	FileWriter file = null;
    	PrintWriter print = null;
    	try {
    		file = new FileWriter("src\\application\\order.txt", true);
    		print = new PrintWriter(file);
    		print.println(pizza.getSelection() + "   " +  "$" + pizza.getPrice());
    		print.close();
    	}
    	catch(FileNotFoundException f) {
  	   		f.printStackTrace();
  	   	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	finally {
    		if(print != null) {
    			print.close();
    		}
    	}

    }
    
    
    public void writeBurgerOrder(Burger burger) {
    	conn = MySqlConnection.ConnectDb();
    	FileWriter file = null;
    	PrintWriter print = null;
    	try {
    		file = new FileWriter("src\\application\\order.txt", true);
    		print = new PrintWriter(file);
    		print.println(burger.getSelection() + "   " + burger.getPrice());
    		print.close();
  	   	}
  	   	catch(FileNotFoundException f) {
  	   		f.printStackTrace();
  	   	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	finally {
    		if(print != null) {
    			print.close();
    		}
    	}

    }
    
    
    public void writeDrinksOrder(Drinks drinks) {
    	conn = MySqlConnection.ConnectDb();
    	FileWriter file = null;
    	PrintWriter print = null;
    	try {
    		file = new FileWriter("src\\application\\order.txt", true);
    		print = new PrintWriter(file);
    		print.println(drinks.getSelection() + "   " + drinks.getPrice());
    		print.close();
    	}
    	catch(FileNotFoundException f) {
  	   		f.printStackTrace();
  	   	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	finally {
    		if(print != null) {
    			print.close();
    		}
    	}
    }
    
    
    public void readOrder() {
    	conn = MySqlConnection.ConnectDb();
    	File file = null;
    	Scanner sc = null;
    	
    	try {
    		file = new File("src\\application\\order.txt");
    		sc = new Scanner(file);
    		
    		String s = String.valueOf(totalPrice);
    		total.appendText(s);
		} 
    	catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	catch(InputMismatchException f) {
    		System.out.println(f.getMessage());
    	}
    }
    
    
    @FXML
    public void confirmOrder() {
    	Alert success = new Alert(AlertType.CONFIRMATION);
    	success.setContentText("Order confirmed");
    	success.showAndWait();
    	txt_cart.clear();
    	total.clear();
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
