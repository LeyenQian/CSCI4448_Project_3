package Customer.Type;

import Customer.Customer;
import Food.FoodFactory;
import FoodStore.FoodStore;

public class Business extends Customer {

    public boolean buy_rolls(FoodStore food_store) {
        try {
            for (int i = 0; i < 2; i ++) {
                this.products.add(this.buy_services(food_store, food_store.get_product(FoodFactory.TYPE_EGG_ROLL)));
                this.products.add(this.buy_services(food_store, food_store.get_product(FoodFactory.TYPE_JELLY_ROLL)));
                this.products.add(this.buy_services(food_store, food_store.get_product(FoodFactory.TYPE_PASTRY_ROLL)));
                this.products.add(this.buy_services(food_store, food_store.get_product(FoodFactory.TYPE_SAUSAGE_ROLL)));
                this.products.add(this.buy_services(food_store, food_store.get_product(FoodFactory.TYPE_SPRING_ROLL)));
            }
        } catch (Exception e) {
            //  business customer will only take their order if it is filled exactly as requested
            System.out.println(e.getMessage());
            return food_store.get_last_error_code() == FoodStore.CODE_NO_INVENTORY ? false : true;
        }
        
        return true;
    }
}
