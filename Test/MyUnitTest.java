package Test;

import org.junit.Test;
import static org.junit.Assert.*;

import Customer.CustomerFactory;
import Customer.Type.*;
import Food.FoodFactory;
import Food.Type.*;
import FoodStore.FoodStore;
import FoodStore.Inventory;
import FoodStore.Records;
import FoodStore.Service.*;
import FoodStore.Service.Type.*;


public class MyUnitTest {
    @Test
    public void test_customer_factory() {
        System.out.print("test_customer_factory...");
        assertTrue("failure - CustomerFactory.create(TYPE_BUSINESS)", CustomerFactory.create(CustomerFactory.TYPE_BUSINESS) instanceof Business);
        assertTrue("failure - CustomerFactory.create(TYPE_CATERING)", CustomerFactory.create(CustomerFactory.TYPE_CATERING) instanceof Catering);
        assertTrue("failure - CustomerFactory.create(TYPE_CASUAL)", CustomerFactory.create(CustomerFactory.TYPE_CASUAL) instanceof Casual);

        assertTrue("failure - CustomerFactory.create(TYPE_BUSINESS, 1)", CustomerFactory.create(CustomerFactory.TYPE_BUSINESS, 1).size() == 1);
        assertTrue("failure - CustomerFactory.create(TYPE_CATERING, 2)", CustomerFactory.create(CustomerFactory.TYPE_CATERING, 2).size() == 2);
        assertTrue("failure - CustomerFactory.create(TYPE_CASUAL, 3)", CustomerFactory.create(CustomerFactory.TYPE_CASUAL, 3).size() == 3);
        System.out.println("ok");
    }

    @Test
    public void test_food_factory() {
        System.out.print("test_food_factory...");
        assertTrue("failure - FoodFactory.create(TYPE_EGG_ROLL)", FoodFactory.create(FoodFactory.TYPE_EGG_ROLL) instanceof EggRoll);
        assertTrue("failure - FoodFactory.create(TYPE_JELLY_ROLL)", FoodFactory.create(FoodFactory.TYPE_JELLY_ROLL) instanceof JellyRoll);
        assertTrue("failure - FoodFactory.create(TYPE_PASTRY_ROLL)", FoodFactory.create(FoodFactory.TYPE_PASTRY_ROLL) instanceof PastryRoll);
        assertTrue("failure - FoodFactory.create(TYPE_SAUSAGE_ROLL)", FoodFactory.create(FoodFactory.TYPE_SAUSAGE_ROLL) instanceof SausageRoll);
        assertTrue("failure - FoodFactory.create(TYPE_SPRING_ROLL)", FoodFactory.create(FoodFactory.TYPE_SPRING_ROLL) instanceof SpringRoll);
        System.out.println("ok");
    }

    @Test
    public void test_service_factory() {
        System.out.print("test_service_factory...");
        Product item = new Product(FoodFactory.create(FoodFactory.TYPE_SPRING_ROLL), 2.0f);
        assertTrue(item instanceof Product);

        item = ServiceFactory.create(ServiceFactory.TYPE_FILLING, item);
        assertTrue(item instanceof Filling);

        item = ServiceFactory.create(ServiceFactory.TYPE_SAUCE, item);
        assertTrue(item instanceof Sauce);

        item = ServiceFactory.create(ServiceFactory.TYPE_TOPPING, item);
        assertTrue(item instanceof Topping);
        System.out.println("ok");
    }

    @Test
    public void test_records() {
        System.out.print("test_records...");
        Records rec = new Records();
        rec.add_record("Business", "SpringRoll", 2.0f, 1);
        rec.add_record("Business", "EggRoll", 2.5f, 2);
        rec.add_record("Catering", "SpringRoll", 2.0f, 2);
        assertTrue("failure - record quantity not match", rec.get_record().size() == 2);
        assertTrue("failure - record quantity not match", rec.get_record().get(1).size() == 1);
        assertTrue("failure - record quantity not match", rec.get_record().get(2).size() == 2);
        System.out.println("ok");
    }

    @Test
    public void test_outage_records() {
        System.out.print("test_outage_records...");
        FoodStore food_store = new FoodStore("220 Summit Blvd");

        for (int i = 0; i < 30; i ++) {
            try {
                food_store.get_product("Business", FoodFactory.TYPE_EGG_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_JELLY_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_PASTRY_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_SAUSAGE_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_SPRING_ROLL);
            } catch (Exception e) {}
        }

        try {
            food_store.get_product("Business", FoodFactory.TYPE_EGG_ROLL);
        } catch (Exception e) {}

        assertTrue("failure - quantity mismatch",  food_store.get_records().get_outage_count() == 1);
        System.out.println("ok");
    }

    @Test
    public void test_inventory() {
        System.out.print("test_inventory...");
        Inventory inv = new Inventory();
        
        inv.insert_product(FoodFactory.TYPE_EGG_ROLL, 30, 3.0f);
        assertTrue("failure - inventory quantity not match", inv.check_quantity(FoodFactory.TYPE_EGG_ROLL) == 30);
        
        Object obj = inv.retrieve_product(FoodFactory.TYPE_EGG_ROLL);
        assertTrue("failure - product type mismatch", obj instanceof Product);
        assertTrue("failure - item type mismatch", ((Product)obj).get_item() instanceof EggRoll);

        System.out.println("ok");
    }

    @Test
    public void test_food_store_config() {
        System.out.print("test_food_store_config...");
        FoodStore food_store = new FoodStore("220 Summit Blvd");
        assertTrue("failure - address mismatch", food_store.get_address() == "220 Summit Blvd");
        
        System.out.println("ok");
    }

    @Test
    public void test_food_store_retrieve_single_roll_type() {
        System.out.print("test_food_store_retrieve_single_roll_type...");
        FoodStore food_store = new FoodStore("220 Summit Blvd");

        for (int i = 0; i < 31; i ++) {
            try {
                food_store.get_product("Business", FoodFactory.TYPE_EGG_ROLL);
            } catch (Exception e) {}
        }
        assertTrue("failure - quantity mismatch", food_store.get_last_error_code() == FoodStore.CODE_OUT_INVENTORY);
        System.out.println("ok");
    }

    @Test
    public void test_food_store_retrieve_all_roll_type() {
        System.out.print("test_food_store_retrieve_all_roll_type...");
        FoodStore food_store = new FoodStore("220 Summit Blvd");

        for (int i = 0; i < 30; i ++) {
            try {
                food_store.get_product("Business", FoodFactory.TYPE_EGG_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_JELLY_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_PASTRY_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_SAUSAGE_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_SPRING_ROLL);
            } catch (Exception e) {}
        }

        try {
            food_store.get_product("Business", FoodFactory.TYPE_EGG_ROLL);
        } catch (Exception e) {}

        assertTrue("failure - quantity mismatch",  food_store.get_last_error_code() == FoodStore.CODE_NO_INVENTORY);
        System.out.println("ok");
    }

    @Test
    public void test_food_store_cover_shortage() {
        System.out.print("test_food_store_cover_shortage...");
        FoodStore food_store = new FoodStore("220 Summit Blvd");

        for (int i = 0; i < 30; i ++) {
            try {
                food_store.get_product("Business", FoodFactory.TYPE_EGG_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_JELLY_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_PASTRY_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_SAUSAGE_ROLL);
                food_store.get_product("Business", FoodFactory.TYPE_SPRING_ROLL);
            } catch (Exception e) {}
        }

        food_store.check_inventory();
        assertTrue("failure - quantity mismatch",  food_store.check_availability(FoodFactory.TYPE_EGG_ROLL, 30));
        assertTrue("failure - quantity mismatch",  food_store.check_availability(FoodFactory.TYPE_JELLY_ROLL, 30));
        assertTrue("failure - quantity mismatch",  food_store.check_availability(FoodFactory.TYPE_PASTRY_ROLL, 30));
        assertTrue("failure - quantity mismatch",  food_store.check_availability(FoodFactory.TYPE_SAUSAGE_ROLL, 30));
        assertTrue("failure - quantity mismatch",  food_store.check_availability(FoodFactory.TYPE_SPRING_ROLL, 30));
        System.out.println("ok");
    }
}