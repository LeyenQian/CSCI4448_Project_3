package FoodStore.Service;

public abstract class ServiceDecorator extends Product {
    protected String type = this.getClass().getSimpleName();
    protected float service_price_coefficient;
    protected Product item;

    public ServiceDecorator(Product item, float service_price_coefficient){
        this.item = item;
        this.service_price_coefficient = service_price_coefficient;
    }

    public String get_description() {
        return item.get_description() + String.format(" <extra %s> ", this.type);
    }

    public float get_price() {
        float price = item.get_price();
        return price + price * service_price_coefficient;
    }
}
