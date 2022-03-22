package com.example.project4;

import java.text.DecimalFormat;

/**
 * This subclass of pizza handles Deluxe Pizzas and its calculations
 * and overrides toString with its own implementation.
 * @author Aayush Jha, Tarik Mahmutbegovic
 */
public class DeluxePizza extends Pizza {

    public static final double formatting = 100d;
    public static final double largePrice = 16.99;
    public static final double mediumPrice = 14.99;
    public static final double smallPrice = 12.99;
    public static final int toppingCount = 5;
    public static final double toppingPrice = 1.49;
    /**
     * Default constructor for Deluxe Pizza, creates a default Deluxe Pizza.
     */
    public DeluxePizza() {
        super();
        toppings.add(Topping.pepperoni);
        toppings.add(Topping.pineapple);
        toppings.add(Topping.sausage);
        toppings.add(Topping.chicken);
        toppings.add(Topping.onion);
    }

    /**
     * Implementation of abstract price method from Pizza super class. Calculates pizza price.
     * @return price
     */
    @Override
    public double price() {
        Double sum = 0.0;
        if(size.equals(Size.large)){
            sum += largePrice;
        }
        else if(size.equals(Size.medium)){
            sum += mediumPrice;
        }
        else{
            sum += smallPrice;
        }
        if(toppings.size() <= toppingCount){
            return sum;
        }
        else{
            sum += (toppings.size() - toppingCount) * toppingPrice;
        }
        sum = (double) Math.round(sum * formatting)/ formatting;
        return sum;
    }

    /**
     * Overridden to string method for deluxe pizza.
     * @return pizzaText
     */
    @Override
    public String toString(){
        return "Deluxe pizza" + super.toString() + ",$" + this.price();
    }
}
