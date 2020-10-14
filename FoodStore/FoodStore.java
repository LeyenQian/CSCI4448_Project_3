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
    private Records records = new Records();
    private int last_error_code = CODE_NO_ERROR;
    private int timestamp;

    public FoodStore(String store_address) {
        // initial inventory
        inventory.insert_product(FoodFactory.TYPE_EGG_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_EGG_ROLL);
        inventory.insert_product(FoodFactory.TYPE_JELLY_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_JELLY_ROLL);
        inventory.insert_product(FoodFactory.TYPE_PASTRY_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_PASTRY_ROLL);
        inventory.insert_product(FoodFactory.TYPE_SAUSAGE_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_SAUSAGE_ROLL);
        inventory.insert_product(FoodFactory.TYPE_SPRING_ROLL, Constants.QUANTITY_ROLL, Constants.PRICE_SPRING_ROLL);
        this.store_address = store_address;
    }

    public String get_address() {
        return this.store_address;
    }

    public void check_inventory() {
        this.inventory.cover_short_position(Constants.QUANTITY_ROLL);
    }

    public void set_timestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void cancel_order(String customer_type) {
        // customer may not take the order, if the quantity cannot meet their requirement
        // in this case, only business will call this function
        records.add_outage_record(customer_type, timestamp);
    }

    public Product get_product(String customer_type, int type) throws Exception {
        // check whether inventory is not empty
        if ( inventory.check_total_quantity() == 0 ) {
            last_error_code = CODE_NO_INVENTORY;
            throw new Exception("#Exec: all roll are sold out, store is going to close today!");
        }

        if ( inventory.check_quantity(type) == 0 ) {
            last_error_code = CODE_OUT_INVENTORY;
            records.add_outage_record(customer_type, timestamp);
            throw new Exception("#Exec: selected roll is sold out!");
        }

        return inventory.retrieve_product(type);
    }

    public void check_bill(String customer_type, String product_type, float price, int timestamp) {
        records.add_record(customer_type, product_type, price, timestamp);
    }

    public void summary_day(int day) {
        records.summary(day);
    }

    public void summary_all(int days) {
        records.summary_all(days);
    }

    public boolean check_availability(int type, int quantity) {
        return inventory.check_quantity(type) >= quantity;
    }

    public int get_last_error_code() {
        return last_error_code;
    }

    public Product get_service(int type, Product item) {
        return ServiceFactory.create(type, item);
    }

    public void show_inventory(String addition_info) {
        System.out.println("\u001B[36m" + addition_info);
        System.out.println(String.format("\u001B[36mEgg     Roll: %d", inventory.check_quantity(FoodFactory.TYPE_EGG_ROLL)));
        System.out.println(String.format("\u001B[36mJelly   Roll: %d", inventory.check_quantity(FoodFactory.TYPE_JELLY_ROLL)));
        System.out.println(String.format("\u001B[36mPastry  Roll: %d", inventory.check_quantity(FoodFactory.TYPE_PASTRY_ROLL)));
        System.out.println(String.format("\u001B[36mSpring  Roll: %d", inventory.check_quantity(FoodFactory.TYPE_SPRING_ROLL)));
        System.out.println(String.format("\u001B[36mSausage Roll: %d", inventory.check_quantity(FoodFactory.TYPE_SAUSAGE_ROLL)));
        System.out.println("\u001B[36m" + addition_info);
    }

    public static void main(String[] args) {
        FoodStore store = new FoodStore("220 Summit Blvd");

        // test before add service
        Product spring_roll = null;
        try {
            spring_roll = store.get_product("", FoodFactory.TYPE_SPRING_ROLL);
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