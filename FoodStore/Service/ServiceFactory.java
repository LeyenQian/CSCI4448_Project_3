package FoodStore.Service;

import FoodStore.Constants;
import FoodStore.Service.Type.Filling;
import FoodStore.Service.Type.Sauce;
import FoodStore.Service.Type.Topping;

public class ServiceFactory {

    public static final int TYPE_FILLING = 0x0;
    public static final int TYPE_SAUCE   = 0x1;
    public static final int TYPE_TOPPING = 0x2;

    public static Product create(int type, Product item) {
        if ( type == TYPE_FILLING ) return new Filling(item, Constants.PRICE_IDX_SERVICE_FILLING);
        if ( type == TYPE_SAUCE ) return new Sauce(item, Constants.PRICE_IDX_SERVICE_SAUCE);
        if ( type == TYPE_TOPPING ) return new Topping(item, Constants.PRICE_IDX_SERVICE_TOPPING);

        return null;
    }
}
