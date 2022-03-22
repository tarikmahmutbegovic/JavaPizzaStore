package com.example.project4;

import java.util.ArrayList;

/**
 * Abstract super class pizza that defines methods to be used and/or
 * implemented by concrete subclass pizzas of various types.
 * @author Aayush Jha, Tarik Mahmutbegovic
 */
public abstract class Pizza {
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;
    static final int maxTopping = 7;

    /**
     * Getter for arraylist of toppings
     * @return toppings
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Getter for size of pizza
     * @return size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Setter for size of pizza
     * @param size
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Default constructor of pizza to be used by subclass constructors.
     */
    public Pizza() {
        this.size = Size.small;
    }

    /**
     * Abstract method to calculate price of pizza to be implemented by concrete subclasses.
     * @return price
     */
    public abstract double price();

    /**
     * Method to add a topping to private arraylist toppings. Returns true if added successfully, false otherwise.
     * @param topping
     * @return addResult
     */
    public boolean addTopping(Topping topping){
        if(toppings.size() >= maxTopping){
            return false;
        }
        return toppings.add(topping);
    }

    /**
     * Method to remove a topping from private arraylist toppings. Returns true if removed successfully,
     * false otherwise.
     * @param topping
     * @return removeResult
     */
    public boolean removeTopping(Topping topping){
        return toppings.remove(topping);
    }

    /**
     * Overridden implementation of toString method to convert Pizza object to desired text format.
     * @return
     */
    @Override
    public String toString(){
        String toReturn = "";
        for(int i = 0; i < toppings.toArray().length; i++){
            toReturn += "," + toppings.get(i).toString();
        }
        toReturn += "," + size.toString();
        return toReturn;
    }
}
