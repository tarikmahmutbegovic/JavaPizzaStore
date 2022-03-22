package com.example.project4;

import java.util.ArrayList;

/**
 * Order class that saves an identifying phone number and the pizzas ordered by that phone number. Can also calculate
 * total price of order and also convert order to text.
 * @author Aayush Jha, Tarik Mahmutbegovic.
 */
public class Order {
    private Long phoneNum;
    private ArrayList<Pizza> pizzas;

    public static final double salesTaxRate = 0.06625;
    /**
     * Constructor to create an order object based on input params.
     * @param phoneNum
     * @param pizzas
     */
    public Order(Long phoneNum, ArrayList<Pizza> pizzas) {
        this.phoneNum = phoneNum;
        this.pizzas = pizzas;
    }

    /**
     * Getter for private instance var phoneNum
     * @return phoneNum
     */
    public Long getPhoneNum() {
        return phoneNum;
    }

    /**
     * Getter for private instance var pizzas
     * @return pizzas.
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Method to calculate total price of order including sales tax.
     * @return totalPrice
     */
    public double totalPrice(){
        double sum = 0;
        for (Pizza pizza: pizzas
             ) {
            sum += pizza.price();
        }
        return sum + sum * salesTaxRate;
    }

    /**
     * Overridden toString method that converts order into text.
     * @return orderText
     */
    @Override
    public String toString(){
        String toReturn = "";
        toReturn += "Phone Number: " + phoneNum + "\n";
        toReturn += "Pizzas: \n";
        for (Pizza pizza: pizzas) {
            toReturn += pizza.toString() + "\n";
        }
        return toReturn;
    }
}
