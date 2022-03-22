package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;


/**
 * Controller class responsible for handling all events occurring in PizzaOrderView.
 * @author Aayush Jha, Tarik Mahmutbegovic
 */
public class PizzaOrderController {

    Pizza pizza;
    String pizzaType;
    private MainController mainController;

    /**
     * Method to get a reference to MainController and to also initialize PizzaOrderView window based on type of pizza
     * user wants to order.
     * @param controller
     * @param Pizzatype
     * @throws FileNotFoundException
     */
    public void setMainController(MainController controller, String Pizzatype) throws FileNotFoundException {
        mainController = controller;
        pizzaType = Pizzatype;
        if(Objects.equals("Deluxe Pizza", pizzaType)){
            setUpDeluxe();
        }
        else if(Objects.equals("Hawaiian Pizza", pizzaType)){
            setUpHawaiian();
        }
        else if(Objects.equals("Pepperoni Pizza", pizzaType)){
            setUpPepperoni();
        }
        sizePicker.getItems().addAll(Size.small, Size.medium, Size.large);
        sizePicker.setValue(Size.small);
    }

    /**
     * Start up method that inits PizzaOrderView window to Deluxe pizza customization.
     */
    private void setUpDeluxe() {
        String filepath = new File("").getAbsolutePath();
        filepath = filepath.concat("\\src");
        String pizzaImagePath = filepath.concat("\\deluxe_pizza.jpg");
        try {
            InputStream stream = new FileInputStream(pizzaImagePath);
            Image image = new Image(stream);
            pizzaImage.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pizza = PizzaMaker.createPizza("deluxe");
        price.setText("12.99");
        ObservableList<Topping> selectToppings = FXCollections.observableArrayList(pizza.getToppings());
        selectedToppings.setItems(selectToppings);
        ObservableList<Topping> remainToppings = FXCollections.observableArrayList(Topping.ham, Topping.mushroom,
                Topping.greenPepper, Topping.redPepper, Topping.jalapeno);
        availableToppings.setItems(remainToppings);
    }

    /**
     * Start up method that inits PizzaOrderView window to Hawaiian pizza customization.
     */
    private void setUpHawaiian() {
        String filepath = new File("").getAbsolutePath();
        filepath = filepath.concat("\\src");
        String pizzaImagePath = filepath.concat("\\hawaiian-pizza-16-1200.jpg");
        try {
            InputStream stream = new FileInputStream(pizzaImagePath);
            Image image = new Image(stream);
            pizzaImage.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pizza = PizzaMaker.createPizza("hawaiian");
        price.setText("10.99");
        ObservableList<Topping> selectToppings = FXCollections.observableArrayList(pizza.getToppings());
        selectedToppings.setItems(selectToppings);
        ObservableList<Topping> remainToppings = FXCollections.observableArrayList(Topping.pepperoni, Topping.mushroom,
        Topping.chicken, Topping.sausage, Topping.onion, Topping.greenPepper, Topping.redPepper, Topping.jalapeno);
        availableToppings.setItems(remainToppings);
    }

    /**
     * Start up method that inits PizzaOrderView window to Pepperoni pizza customization.
     */
    private void setUpPepperoni() {
        String filepath = new File("").getAbsolutePath();
        filepath = filepath.concat("\\src");
        String pizzaImagePath = filepath.concat("\\pepperoni_pizza.jpg");
        try {
            InputStream stream = new FileInputStream(pizzaImagePath);
            Image image = new Image(stream);
            pizzaImage.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pizza = PizzaMaker.createPizza("pepperoni");
        price.setText("8.99");
        ObservableList<Topping> selectToppings = FXCollections.observableArrayList(pizza.getToppings());
        selectedToppings.setItems(selectToppings);
        ObservableList<Topping> remainToppings = FXCollections.observableArrayList(Topping.pineapple, Topping.ham,
                Topping.chicken, Topping.mushroom, Topping.sausage, Topping.onion,
                Topping.greenPepper, Topping.redPepper, Topping.jalapeno);
        availableToppings.setItems(remainToppings);
    }


    @FXML
    private ImageView pizzaImage;

    @FXML
    private ComboBox<Size> sizePicker;

    @FXML
    private ListView<Topping> availableToppings;

    @FXML
    private ListView<Topping> selectedToppings;

    @FXML
    private Button add;

    @FXML
    private Button remove;

    @FXML
    private TextField price;

    @FXML
    private Button addOrder;

    /**
     * Event handler for sizePicker combobox, updates the price TextField whenever size is changed.
     * @param event
     */
    @FXML
    public void sizePriceCalc(ActionEvent event){
        pizza.setSize(sizePicker.getValue());
        price.setText(String.valueOf(pizza.price()));
    }

    /**
     * Event handler for Add button. Attempts to add the selected topping to pizza, alerts the user if not possible
     * to add the selected topping.
     * @param event
     */
    @FXML
    public void addTopping(ActionEvent event){
        Topping topping = availableToppings.getSelectionModel().getSelectedItem();
        if(topping == null){
            return;
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cannot add this topping.");
        alert.setContentText("You are trying to add more than 7 toppings, only a maximum of 7 are allowed.");
        if(!pizza.addTopping(topping)){
            alert.showAndWait();
            return;
        }
        selectedToppings.getItems().add(topping);
        availableToppings.getItems().remove(topping);
        price.setText(String.valueOf(pizza.price()));
    }

    /**
     * Event handler for Remove button. Attempts to remove the selected topping from the pizza, alerts user if
     * they are removing an essential topping from the pizza.
     * @param event
     */
    @FXML
    public void removeTopping(ActionEvent event){
        Topping topping = selectedToppings.getSelectionModel().getSelectedItem();
        if(topping == null){
            return;
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("You are removing an essential topping.");
        alert.setContentText("");
        if(Objects.equals(pizzaType, "Deluxe Pizza")){
            if(topping == Topping.pepperoni || topping == Topping.pineapple || topping == Topping.sausage ||
                    topping == Topping.chicken || topping == Topping.onion){
                alert.showAndWait();
            }
        }
        else if(Objects.equals("Hawaiian Pizza", pizzaType)){
            if(topping == Topping.ham || topping == Topping.pineapple){
                alert.showAndWait();
            }
        }
        else if(Objects.equals("Pepperoni Pizza", pizzaType)){
            if(topping == Topping.pepperoni){
                alert.showAndWait();
            }
        }
        pizza.removeTopping(topping);
        selectedToppings.getItems().remove(topping);
        availableToppings.getItems().add(topping);
        price.setText(String.valueOf(pizza.price()));
    }

    /**
     * Event handler for Add Order button. Adds the pizza to the current order of the user.
     * @param event
     */
    @FXML
    public void addToOrder(ActionEvent event){
        mainController.currentPizzas.add(pizza);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Adding pizza to order.");
        alert.showAndWait();
        Stage stage = (Stage) addOrder.getScene().getWindow();
        stage.close();
    }

}
