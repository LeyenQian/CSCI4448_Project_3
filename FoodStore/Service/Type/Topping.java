package FoodStore.Service.Type;

import FoodStore.Service.Product;
import FoodStore.Service.ServiceDecorator;

public class Topping extends ServiceDecorator {

    public Topping(Product item, float service_price_coefficient) {
        super(item, service_price_coefficient);
    }
    
}
