package com.example.project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * StoreOrders class saves all orders placed by store. Handles all orders placed and
 * can manipulate the list or export them to given text file.
 * @author Aayush Jha, Tarik Mahmutbegovic
 */
public class StoreOrders {
    private ArrayList<Order> orders;

    /**
     * Constructor to create a StoreOrders object.
     * @param orders
     */
    public StoreOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    /**
     * Getter for arraylist of orders.
     * @return orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Attempts to add input order to arraylist of orders.
     * @param order
     * @return addResult
     */
    public boolean addOrder(Order order){
        return orders.add(order);
    }

    /**
     * Attempts to remove input order from arraylist of orders.
     * @param order
     * @return removeResult
     */
    public boolean removeOrder(Order order){
        return orders.remove(order);
    }

    /**
     * Writes all saved orders from arraylist to given file.
     * @param targetFile
     * @throws FileNotFoundException
     */
    public void export(File targetFile) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(targetFile);
        for (Order order: orders) {
            pw.println(order.toString());
        }
        pw.close();
    }
}
