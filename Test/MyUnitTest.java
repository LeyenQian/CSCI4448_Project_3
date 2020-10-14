package Test;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import Customer.CustomerFactory;
import Customer.Type.*;
import Food.FoodFactory;
import Food.Type.*;
import FoodStore.Service.*;
import FoodStore.Service.Type.*;


public class MyUnitTest {
    @Test
    public void test_customer_factory() {
        assertTrue(CustomerFactory.create(CustomerFactory.TYPE_BUSINESS) instanceof Business);
        assertTrue(CustomerFactory.create(CustomerFactory.TYPE_CATERING) instanceof Catering);
        assertTrue(CustomerFactory.create(CustomerFactory.TYPE_CASUAL) instanceof Casual);
    }

    @Test
    public void test_food_factory() {
        assertTrue(FoodFactory.create(FoodFactory.TYPE_EGG_ROLL) instanceof EggRoll);
        assertTrue(FoodFactory.create(FoodFactory.TYPE_JELLY_ROLL) instanceof JellyRoll);
        assertTrue(FoodFactory.create(FoodFactory.TYPE_PASTRY_ROLL) instanceof PastryRoll);
        assertTrue(FoodFactory.create(FoodFactory.TYPE_SAUSAGE_ROLL) instanceof SausageRoll);
        assertTrue(FoodFactory.create(FoodFactory.TYPE_SPRING_ROLL) instanceof SpringRoll);
    }

    @Test
    public void test_service_factory() {
        Product item = new Product(FoodFactory.create(FoodFactory.TYPE_SPRING_ROLL), 2.0f);
        assertTrue(item instanceof Product);

        item = ServiceFactory.create(ServiceFactory.TYPE_FILLING, item);
        assertTrue(item instanceof Filling);

        item = ServiceFactory.create(ServiceFactory.TYPE_SAUCE, item);
        assertTrue(item instanceof Sauce);

        item = ServiceFactory.create(ServiceFactory.TYPE_TOPPING, item);
        assertTrue(item instanceof Topping);
    }
}