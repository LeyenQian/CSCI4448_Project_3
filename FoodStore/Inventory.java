package FoodStore;

import java.util.HashMap;

import Food.Food;
import Food.FoodFactory;

public class Inventory {
    private HashMap<Integer, Integer> records = new HashMap<Integer, Integer>();

    public int check_quantity(int type) {
        return records.containsKey(type) ? records.get(type) : 0;
    }

    public void cover_short_position(int quantity) {
        records.replaceAll((k, v) -> v == 0 ? quantity : v);
    }

    public void cover_short_position(int type, int quantity) {
        records.replace(type, quantity);
    }

    public void del_product(int type) {
        records.remove(type);
    }

    public Food get_product(int type) {
        int quantity = this.check_quantity(type);
        if (quantity > 0) {
            records.replace(type, quantity - 1);
            return FoodFactory.create(type);
        }
        return null;
    }
}
