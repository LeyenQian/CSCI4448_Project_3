package FoodStore;

import java.util.HashMap;
import java.util.Map;

import Food.FoodFactory;
import FoodStore.Service.Product;

public class Inventory {
    private Map<Integer, Integer> quantity_records = new HashMap<>();
    private Map<Integer, Float> price_records = new HashMap<>();
    
    public int check_quantity(int type) {
        return quantity_records.containsKey(type) ? quantity_records.get(type) : 0;
    }

    public void cover_short_position(int quantity) {
        quantity_records.replaceAll((k, v) -> v == 0 ? quantity : v);
    }

    public void cover_short_position(int type, int quantity) {
        quantity_records.replace(type, quantity);
    }

    public void change_price(int type, float price) {
        price_records.replace(type, price);
    }

    public void remove_product(int type) {
        quantity_records.remove(type);
        price_records.remove(type);
    }

    public void insert_product(int type, int quantity, float price) {
        quantity_records.put(type, quantity);
        price_records.put(type, price);
    }

    public Product retrieve_product(int type) {
        int quantity = this.check_quantity(type);
        if (quantity > 0) {
            quantity_records.replace(type, quantity - 1);
            return new Product(FoodFactory.create(type), price_records.get(type));
        }
        return null;
    }
}
