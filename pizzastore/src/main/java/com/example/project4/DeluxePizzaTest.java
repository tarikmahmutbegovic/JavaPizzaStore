package com.example.project4;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeluxePizzaTest {

    @Test
    public void price() {
        DeluxePizza pizza1 = new DeluxePizza();
        assertTrue(pizza1.price() == 12.99); //Small default deluxe pizza

        pizza1.removeTopping(Topping.pepperoni);
        assertTrue(pizza1.price() == 12.99); //Removing topping below default 5 should not change price

        pizza1.addTopping(Topping.pepperoni);
        pizza1.addTopping(Topping.mushroom);
        assertTrue(pizza1.price() == 14.48); //Small pizza with 6 toppings increases price by 1.49

        pizza1.addTopping(Topping.ham);
        assertTrue(pizza1.price() == 15.97); //Small pizza with 7 toppings increases price by 2.98

        DeluxePizza pizza2 = new DeluxePizza();
        pizza2.setSize(Size.medium);
        assertTrue(pizza2.price() == 14.99); //Medium default deluxe pizza

        pizza2.removeTopping(Topping.pepperoni);
        assertTrue(pizza2.price() == 14.99); //Removing topping below default 5 should not change price

        pizza2.addTopping(Topping.pepperoni);
        pizza2.addTopping(Topping.mushroom);
        assertTrue(pizza2.price() == 16.48); //Pizza with 6 toppings increases price by 1.49

        pizza2.addTopping(Topping.ham);
        assertTrue(pizza2.price() == 17.97); //Pizza with 7 toppings increases price by 2.98

        DeluxePizza pizza3 = new DeluxePizza();
        pizza3.setSize(Size.large);
        assertTrue(pizza3.price() == 16.99); // Large default deluxe pizza

        pizza3.removeTopping(Topping.pepperoni);
        assertTrue(pizza3.price() == 16.99); //Removing topping below default 5 should not change price

        pizza3.addTopping(Topping.pepperoni);
        pizza3.addTopping(Topping.mushroom);
        assertTrue(pizza3.price() == 18.48); //Pizza with 6 toppings increases price by 1.49


        pizza3.addTopping(Topping.ham);
        assertTrue(pizza3.price() == 19.97); //Pizza with 7 toppings increases price by 2.98

    }
}