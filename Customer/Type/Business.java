package Customer.Type;

import Customer.Customer;
import Food.FoodFactory;
import FoodStore.FoodStore;

public class Business extends Customer {

    public boolean buy_rolls(FoodStore food_store) {

        // A business customer will only take their order if it is filled exactly as requested, otherwise, they will not make a purchase.
        if ( !buy_rolls_helper(food_store, FoodFactory.TYPE_EGG_ROLL, 2, true) ) return false;
        if ( !buy_rolls_helper(food_store, FoodFactory.TYPE_JELLY_ROLL, 2, true) ) return false;
        if ( !buy_rolls_helper(food_store, FoodFactory.TYPE_PASTRY_ROLL, 2, true) ) return false;
        if ( !buy_rolls_helper(food_store, FoodFactory.TYPE_SAUSAGE_ROLL, 2, true) ) return false;
        if ( !buy_rolls_helper(food_store, FoodFactory.TYPE_SPRING_ROLL, 2, true) ) return false;
        
        return true;
    }
}
