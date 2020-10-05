package FoodStore;

public class Product {
    private Object item;
    private float price;

    public Product(Object item, float price) {
        this.set_item(item);
        this.set_price(price);
    }

    public void set_price(float price) {
        this.price = price;
    }

    public float get_price() {
        return this.price;
    }

    public void set_item(Object item) {
        this.item = item;
    }

    public Object get_item() {
        return this.item;
    }
}
