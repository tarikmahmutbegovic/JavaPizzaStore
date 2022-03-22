package com.example.project4;

/**
 * This subclass of pizza handles Pepperoni Pizzas and its calculations and
 * overrides toString with its own implementation.
 * @author Aayush Jha, Tarik Mahmutbegovic
 */
public class PepperoniPizza extends Pizza{

    public static final double formatting = 100d;
    public static final double largePrice = 12.99;
    public static final double mediumPrice = 10.99;
    public static final double smallPrice = 8.99;
    public static final int toppingCount = 1;
    public static final double toppingPrice = 1.49;

    /**
     * Default constructor for Pepperoni Pizza, creates a default Pepperoni Pizza.
     */
    public PepperoniPizza() {
        super();
        toppings.add(Topping.pepperoni);
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
     * Overridden to string method for pepperoni pizza.
     * @return pizzaText
     */
    @Override
    public String toString(){
        return "Pepperoni pizza" + super.toString() + ",$" + this.price();
    }
}
