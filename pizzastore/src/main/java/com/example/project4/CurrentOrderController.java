package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Controller class responsible for handling all events in the Current Order window. Capable of altering order
 * and saving it to store orders list.
 * @author Aayush Jha, Tarik Mahmutbegovic
 */
public class CurrentOrderController {

    public static final double salesTaxRate = 0.06625;
    private MainController mainController;

    /**
     * Method that saves a reference to MainController class and also serves as set up for the Current Order window.
     * @param controller
     */
    public void setMainController(MainController controller) {
        mainController = controller;
        phoneNumber.setText(String.valueOf(mainController.phone));
        ArrayList<String> tempPizzas = new ArrayList<>();
        double subTote = 0.0;
        for (Pizza pizza: mainController.currentPizzas) {
            tempPizzas.add(pizza.toString());
            subTote += pizza.price();
        }
        double taxTotal = subTote * salesTaxRate;
        subtotal.setText(String.format("%.2f", subTote));
        salesTax.setText(String.format("%.2f", taxTotal));
        double temp = subTote + taxTotal;
        orderTotal.setText(String.format("%.2f", temp));
        ObservableList<String> currPizzas = FXCollections.observableArrayList(tempPizzas);
        pizzas.setItems(currPizzas);
    }

    @FXML
    private TextField phoneNumber;

    @FXML
    private ListView<String> pizzas;

    @FXML
    private TextField subtotal;

    @FXML
    private TextField salesTax;

    @FXML
    private TextField orderTotal;

    @FXML
    private Button removePizzaButton;

    @FXML
    private Button placeOrderButton;

    /**
     * Method that updates all price fields according to current order values.
     */
    public void recalc(){
        double subTote = 0.0;
        for (Pizza pizza: mainController.currentPizzas) {
            subTote += pizza.price();
        }
        double taxTotal = subTote * salesTaxRate;
        subtotal.setText(String.format("%.2f", subTote));
        salesTax.setText(String.format("%.2f", taxTotal));
        double temp = subTote + taxTotal;
        orderTotal.setText(String.format("%.2f", temp));
    }

    /**
     * Handler for removing selected pizza from the current order.
     * @param event
     */
    @FXML
    public void removePizza(ActionEvent event){
        ObservableList<Integer> index = pizzas.getSelectionModel().getSelectedIndices();
        if(index == null || index.isEmpty()){
            return;
        }
        int foo = index.get(0);
        mainController.currentPizzas.remove(foo);
        pizzas.getItems().remove(foo);
        recalc();
    }

    /**
     * Handler for placing the order and saving it to the list of Store orders.
     * @param event
     */
    @FXML
    public void placeOrder(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(mainController.currentPizzas.size() == 0){
            alert.setContentText("There are no pizzas in the order, canceling order.");
            alert.showAndWait();
            Stage stage = (Stage) placeOrderButton.getScene().getWindow();
            stage.close();
        }
        ArrayList<Pizza> foobar;
        foobar = (ArrayList<Pizza>) mainController.currentPizzas.clone();
        Order order = new Order(mainController.phone, foobar);
        mainController.orders.addOrder(order);
        mainController.currentPizzas.clear();
        Stage stage = (Stage) placeOrderButton.getScene().getWindow();
        stage.close();
    }

}
