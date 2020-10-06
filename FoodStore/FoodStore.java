package FoodStore;

import Food.FoodFactory;

public class FoodStore {
    public static final int INVERNTORY_ROLL = 30;

    public static final float PRICE_EXTRA_SAUCE   = 2.0f;
    public static final float PRICE_EXTRA_TOPPING = 1.5f;
    public static final float PRICE_EXTRA_FILLING = 3.0f;

    public static final float PRICE_EGG_ROLL     = 5.0f;
    public static final float PRICE_JELLY_ROLL   = 5.5f;
    public static final float PRICE_PASTRY_ROLL  = 6.0f;
    public static final float PRICE_SAUSAGE_ROLL = 4.5f;
    public static final float PRICE_SPRING_ROLL  = 4.99f;

    private Inventory inventory = new Inventory();

    public FoodStore() {
        // initial inventory
        inventory.insert_product(FoodFactory.TYPE_EGG_ROLL, INVERNTORY_ROLL, PRICE_EGG_ROLL);
        inventory.insert_product(FoodFactory.TYPE_JELLY_ROLL, INVERNTORY_ROLL, PRICE_JELLY_ROLL);
        inventory.insert_product(FoodFactory.TYPE_PASTRY_ROLL, INVERNTORY_ROLL, PRICE_PASTRY_ROLL);
        inventory.insert_product(FoodFactory.TYPE_SAUSAGE_ROLL, INVERNTORY_ROLL, PRICE_SAUSAGE_ROLL);
        inventory.insert_product(FoodFactory.TYPE_SPRING_ROLL, INVERNTORY_ROLL, PRICE_SPRING_ROLL);
    }

    public Product get_product(int type) {
        return inventory.retrieve_product(type);
    }

    

}
