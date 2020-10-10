package FoodStore;

import Food.FoodFactory;
import FoodStore.Service.Product;
import FoodStore.Service.ServiceFactory;

public class FoodStore {

    private String store_address;
    public static int CODE_NO_ERROR = 0x0;
    public static int CODE_NO_INVENTORY = 0x1;     // all type of roll are sold out (stroe shall close)
    public static int CODE_OUT_INVENTORY = 0x2;    // one type of roll is  sold out
    private Inventory inventory = new Inventory();
    private int last_error_code = CODE_NO_ERROR;

    public FoodStore(String store_address) {
        // initial inventory
        inventory.insert_product(FoodFactory.TYPE_EGG_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_EGG_ROLL);
        inventory.insert_product(FoodFactory.TYPE_JELLY_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_JELLY_ROLL);
        inventory.insert_product(FoodFactory.TYPE_PASTRY_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_PASTRY_ROLL);
        inventory.insert_product(FoodFactory.TYPE_SAUSAGE_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_SAUSAGE_ROLL);
        inventory.insert_product(FoodFactory.TYPE_SPRING_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_SPRING_ROLL);
    }

    public Product get_product(int type) throws Exception {
        // check whether inventory is not empty
        if ( inventory.check_total_quantity() == 0 ) {
            last_error_code = CODE_NO_INVENTORY;
            stop_operation();
            throw new Exception("#Exec: all roll are sold out, store is going to close today!");
        }
        if ( inventory.check_quantity(type) == 0 ) {
            last_error_code = CODE_OUT_INVENTORY;
            throw new Exception("#Exec: selected roll is sold out, please select another roll type!");
        }

        return inventory.retrieve_product(type);
    }

    public int get_last_error_code() {
        return last_error_code;
    }

    public void stop_operation() {
        inventory.cover_short_position(Constants.QUANTITY_ROLL);
    }

    public Product get_service(int type, Product item) {
        return ServiceFactory.create(type, item);
    }

    public static void main(String[] args) {
        FoodStore store = new FoodStore("220 Summit Blvd");

        // test before add service
        Product spring_roll = null;
        try {
            spring_roll = store.get_product(FoodFactory.TYPE_SPRING_ROLL);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        
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
