package FoodStore.Service.Type;

import FoodStore.Service.Product;
import FoodStore.Service.ServiceDecorator;

public class Sauce extends ServiceDecorator {

    public Sauce(Product item, float service_price_coefficient) {
        super(item, service_price_coefficient);
    }
    
}
