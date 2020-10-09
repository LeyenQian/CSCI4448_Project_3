package Customer;

public abstract class Customer {
    protected String type = this.getClass().getSimpleName();

    public String toString() {
        return this.hashCode() + " <" + this.type + "> ";
    }
}
