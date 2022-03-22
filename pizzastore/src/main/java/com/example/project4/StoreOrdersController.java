package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Controller responsible for handling all events in StoreOrdersView window.
 * @author Aayush Jha, Tarik Mahmutbegovic
 */
public class StoreOrdersController {
    Order currOrder;
    private MainController mainController;

    /**
     * Method to get reference to MainController and also to initialize StoreOrdersView window.
     * @param controller
     * @throws FileNotFoundException
     */
    public void setMainController(MainController controller) throws FileNotFoundException {
        mainController = controller;

        currOrder = mainController.orders.getOrders().get(0);

        for (Order order: mainController.orders.getOrders()) {
            phoneNumbers.getItems().add(order.getPhoneNum());
        }

        ArrayList<String> tempPizzas = new ArrayList<>();
        for (Pizza pizza: currOrder.getPizzas()) {
            tempPizzas.add(pizza.toString());
        }

        ObservableList<String> currPizzas = FXCollections.observableArrayList(tempPizzas);
        ordersView.setItems(currPizzas);

        orderTotal.setText(String.format("%.2f", currOrder.totalPrice()));
        phoneNumbers.setValue(currOrder.getPhoneNum());
    }

    @FXML
    private ComboBox<Long> phoneNumbers;

    @FXML
    private TextField orderTotal;

    @FXML
    private ListView<String> ordersView;

    @FXML
    private Button cancelOrderButton;

    @FXML
    private Button exportStoreOrdersButton;

    /**
     * Event handler for phoneNumbers ComboBox that updates window with order corresponding to selected phone number.
     * @param event
     */
    @FXML
    public void changeNumber(ActionEvent event){
        if(mainController.orders.getOrders().isEmpty()){
            return;
        }
        for (Order order: mainController.orders.getOrders()) {
            if(order.getPhoneNum() == phoneNumbers.getValue()){
                currOrder = order;
                break;
            }
        }
        ArrayList<String> foobar = new ArrayList<>();
        for (Pizza pizza: currOrder.getPizzas()) {
            foobar.add(pizza.toString());
        }
        ObservableList<String> currPizzas = FXCollections.observableArrayList(foobar);
        ordersView.setItems(currPizzas);
        orderTotal.setText(String.format("%.2f", currOrder.totalPrice()));
    }

    /**
     * Event handler for cancelOrderButton. Attempts to remove the selected order from list of orders.
     * @param event
     */
    @FXML
    public void cancelOrder(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cannot Cancel Order.");
        alert.setContentText("There are no orders left to cancel.");
        if(mainController.orders.getOrders().isEmpty()){
            alert.showAndWait();
            return;
        }
        mainController.orders.removeOrder(currOrder);

        phoneNumbers.getItems().remove(currOrder.getPhoneNum());

        if(mainController.orders.getOrders().isEmpty()){
            phoneNumbers.getItems().clear();
            ordersView.getItems().clear();
            orderTotal.setText("0.00");
            return;
        }

        currOrder = mainController.orders.getOrders().get(0);
        phoneNumbers.setValue(currOrder.getPhoneNum());

        ArrayList<String> foobar = new ArrayList<>();
        for (Pizza pizza: currOrder.getPizzas()) {
            foobar.add(pizza.toString());
        }
        ObservableList<String> currPizzas = FXCollections.observableArrayList(foobar);
        ordersView.setItems(currPizzas);
        orderTotal.setText(String.format("%.2f", currOrder.totalPrice()));
    }

    /**
     * Event handler for Export button. Allows user to pick a location for the text file and then writes all
     * currently placed orders in text format in the chosen file/location.
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    public void exportStoreOrders(ActionEvent event) throws FileNotFoundException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage);
        if(targetFile == null){
            return;
        }
        mainController.orders.export(targetFile);
        Stage foo = (Stage) orderTotal.getScene().getWindow();
        foo.close();
    }
}
