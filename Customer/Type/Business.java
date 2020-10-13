package Customer.Type;

import Customer.Customer;
import Food.FoodFactory;
import FoodStore.FoodStore;

public class Business extends Customer {

    public boolean buy_rolls(FoodStore food_store) {

        if ( !this.buy_rolls_helper(food_store, FoodFactory.TYPE_EGG_ROLL, 2) ) return false;
        if ( !this.buy_rolls_helper(food_store, FoodFactory.TYPE_JELLY_ROLL, 2) ) return false;
        if ( !this.buy_rolls_helper(food_store, FoodFactory.TYPE_PASTRY_ROLL, 2) ) return false;
        if ( !this.buy_rolls_helper(food_store, FoodFactory.TYPE_SAUSAGE_ROLL, 2) ) return false;
        if ( !this.buy_rolls_helper(food_store, FoodFactory.TYPE_SPRING_ROLL, 2) ) return false;
        
        return true;
    }
}
