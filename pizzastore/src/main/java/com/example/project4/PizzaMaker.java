package com.example.project4;

import java.util.Objects;

/**
 * Class responsible for creating desired pizza type based on user input. Based on "Factory Method" design pattern.
 * @author Aayush Jha, Tarik Mahmutbegovic
 */
public class PizzaMaker {
    /**
     * Static method that creates and returns desired pizza type based on param flavor.
     * @param flavor
     * @return pizza
     */
    public static Pizza createPizza(String flavor){
        if(Objects.equals("deluxe", flavor)){
            DeluxePizza pizza = new DeluxePizza();
            return pizza;
        }
        else if(Objects.equals("hawaiian", flavor)){
            HawaiianPizza pizza = new HawaiianPizza();
            return pizza;
        }
        else{
            PepperoniPizza pizza = new PepperoniPizza();
            return pizza;
        }
    }
}
