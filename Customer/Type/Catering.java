package Customer.Type;

import Customer.Customer;
import FoodStore.FoodStore;

public class Catering extends Customer {

    public boolean buy_rolls(FoodStore food_store) {
        int roll_needed = 15;
        // Catering customers will buy 5 rolls of 3 different types (roll_types have shuffled alread in customer's constructor)
        if ( !buy_rolls_helper(food_store, roll_types.get(0), 5, false) ) return false;
        if ( !buy_rolls_helper(food_store, roll_types.get(1), 5, false) ) return false;
        if ( !buy_rolls_helper(food_store, roll_types.get(2), 5, false) ) return false;

        // take any number of available rolls of any available types up to a total of 15, if their original order cannot be placed
        if ( roll_needed - products.size() > 0 && !buy_rolls_helper(food_store, roll_types.get(3), roll_needed - products.size(), false) ) return false;
        if ( roll_needed - products.size() > 0 && !buy_rolls_helper(food_store, roll_types.get(4), roll_needed - products.size(), false) ) return false;
        return true;
    }
}