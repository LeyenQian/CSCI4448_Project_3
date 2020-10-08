package FoodStore;

import Food.FoodFactory;
import FoodStore.Service.Product;
import FoodStore.Service.ServiceFactory;

public class FoodStore {

    private Inventory inventory = new Inventory();

    public FoodStore() {
        // initial inventory
        inventory.insert_product(FoodFactory.TYPE_EGG_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_EGG_ROLL);
        inventory.insert_product(FoodFactory.TYPE_JELLY_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_JELLY_ROLL);
        inventory.insert_product(FoodFactory.TYPE_PASTRY_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_PASTRY_ROLL);
        inventory.insert_product(FoodFactory.TYPE_SAUSAGE_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_SAUSAGE_ROLL);
        inventory.insert_product(FoodFactory.TYPE_SPRING_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_SPRING_ROLL);
    }

    public Product get_product(int type) {
        return inventory.retrieve_product(type);
    }

    public Product get_service(int type, Product item) {
        return ServiceFactory.create(type, item);
    }


    public static void main(String[] args) {
        FoodStore store = new FoodStore();

        // test before add service
        Product spring_roll = store.get_product(FoodFactory.TYPE_SPRING_ROLL);
        System.out.println(spring_roll.get_description());
        System.out.println(spring_roll.get_price());
        
        // test after add service <filling>
        spring_roll = store.get_service(ServiceFactory.TYPE_FILLING, spring_roll);
        System.out.println(spring_roll.get_description());
        System.out.println(spring_roll.get_price());

        // test after add service <topping>
        spring_roll = store.get_service(ServiceFactory.TYPE_TOPPING, spring_roll);
        System.out.println(spring_roll.get_description());
        System.out.println(spring_roll.get_price());
    }
}
