package Customer;

import FoodStore.FoodStore;

public abstract class Customer {
    protected String type = this.getClass().getSimpleName();

    public String toString() {
        return this.hashCode() + " <" + this.type + "> ";
    }

    // each type of customer has different preference on purchasing rolls, they shall be implemented sperately
    public abstract void buy_rolls(FoodStore food_store);
}
