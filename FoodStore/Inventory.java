package FoodStore;

import java.util.HashMap;

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

    public Product get_product(int type, float price) {
        int quantity = this.check_quantity(type);
        if (quantity > 0) {
            records.replace(type, quantity - 1);
            return new Product(FoodFactory.create(type), price);
        }
        return null;
    }
}
