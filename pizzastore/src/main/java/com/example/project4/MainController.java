package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Responsible for general functionality of Pizzeria GUI. Main/parent window to all other windows.
 * @author Aayush Jha, Tarik Mahmutbegovic
 */
public class MainController implements Initializable {

    ArrayList<Order> foobar = new ArrayList<>();
    StoreOrders orders = new StoreOrders(foobar);

    ArrayList<Pizza> currentPizzas = new ArrayList<Pizza>();

    @FXML
    private ImageView deluxePizzaImage;
    @FXML
    private ImageView hawaiianPizzaImage;
    @FXML
    private ImageView peppPizzaImage;
    @FXML
    private ImageView shopCartImage;
    @FXML
    private ImageView storeFrontImage;

    @FXML
    private TextField phoneNum;

    @FXML
    private Button deluxePizzaOrder;

    @FXML
    private Button hawaiianPizzaOrder;

    @FXML
    private Button pepperoniPizzaOrder;

    @FXML
    private Button currentOrder;

    @FXML
    private Button storeOrders;

    public static final int v = 700;
    public static final int v1 = 900;
    public static final int v2 = 800;

    /**
     * General method that starts up PizzaOrderView window based on pizzaType param.
     * @param pizzaType
     */
    private void pizzaOrderSetup(String pizzaType) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ordering Pizzas");
        alert.setContentText("Starting a new order!");
        alert.showAndWait();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/project4/PizzaOrderView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), v, v1);
            Stage stage = new Stage();
            stage.setTitle("Pizza Customization");
            stage.setScene(scene);
            stage.show();
            PizzaOrderController pizzaOrderView = fxmlLoader.getController();
            pizzaOrderView.setMainController(this, pizzaType);
        } catch (IOException e) {
        }

    }

    /**
     * Event handler for Deluxe Pizza button. Calls pizzaOrderSetUp with "Deluxe Pizza" param.
     * @param event
     */
    @FXML
    public void deluxePizzaOrderSetUp(ActionEvent event) {
        pizzaOrderSetup("Deluxe Pizza");
    }

    /**
     * Event handler for Hawaiian Pizza button. Calls pizzaOrderSetUp with "Hawaiian Pizza" param.
     * @param event
     */
    @FXML
    public void hawaiianPizzaOrderSetUp(ActionEvent event) {
        pizzaOrderSetup("Hawaiian Pizza");
    }

    /**
     * Event handler for Pepperoni Pizza button. Calls pizzaOrderSetUp with "Pepperoni Pizza" param.
     * @param event
     */
    @FXML
    public void pepperoniPizzaOrderSetUp(ActionEvent event) {
        pizzaOrderSetup("Pepperoni Pizza");
    }

    public Long phone;

    /**
     * Event handler for Current Order Button. Starts up according window CurrentOrderView if phone number is valid.
     * @param event
     */
    @FXML
    public void currentOrderSetup(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Phone Number.");
        alert.setContentText("Please enter a valid phone number.");
        if(phoneNum.getText() == null || phoneNum.getText().length() < 9 || phoneNum.getText().length() > 10){
            alert.showAndWait();
            return;
        }
        try {
            phone = Long.parseLong(phoneNum.getText());
        } catch (NumberFormatException e){
            alert.showAndWait();
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/project4/CurrentOrderView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), v2, v2);
            Stage stage = new Stage();
            stage.setTitle("Current Order");
            stage.setScene(scene);
            stage.show();
            CurrentOrderController currentOrderView = fxmlLoader.getController();
            currentOrderView.setMainController(this);
        } catch (IOException e) {
        }
        phoneNum.setText("");
    }

    /**
     * Event handler for Store Orders button. Starts up according StoreOrdersView window,
     * if there are valid saved orders.
     * @param event
     */
    @FXML
    public void storeOrdersSetup(ActionEvent event){
        if(orders.getOrders().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Store Orders is empty.");
            alert.setContentText("Please place some orders");
            alert.showAndWait();
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/project4/StoreOrdersView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), v2, v2);
            Stage stage = new Stage();
            stage.setTitle("Store Orders");
            stage.setScene(scene);
            stage.show();
            StoreOrdersController storeOrdersView = fxmlLoader.getController();
            storeOrdersView.setMainController(this);
        } catch (IOException e) {
        }
    }

    /**
     * Overridden Initialize function that is responsible for setting up various images in MainView window.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String filepath = new File("").getAbsolutePath();
        filepath = filepath.concat("\\src");
        String deluxePizzaImagePath = filepath.concat("\\deluxe_pizza.jpg");
        String peppPizzaImagePath = filepath.concat("\\pepperoni_pizza.jpg");
        String hawaiianPizzaImagePath = filepath.concat("\\hawaiian-pizza-16-1200.jpg");
        String shopCartImagePath = filepath.concat("\\shopping_cart.jpg");
        String storeFrontImagePath = filepath.concat("\\storefront.jpg");
        InputStream stream;
        Image image;
        try {
            stream = new FileInputStream(deluxePizzaImagePath);
            image = new Image(stream);
            deluxePizzaImage.setImage(image);
            stream = new FileInputStream(peppPizzaImagePath);
            image = new Image(stream);
            peppPizzaImage.setImage(image);
            stream = new FileInputStream(hawaiianPizzaImagePath);
            image = new Image(stream);
            hawaiianPizzaImage.setImage(image);
            stream = new FileInputStream(shopCartImagePath);
            image = new Image(stream);
            shopCartImage.setImage(image);
            stream = new FileInputStream(storeFrontImagePath);
            image = new Image(stream);
            storeFrontImage.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}