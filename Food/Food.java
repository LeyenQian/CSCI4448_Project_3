package Food;

public abstract class Food {
    protected String type = this.getClass().getSimpleName();

    public String toString() {
        return this.type;
    }
}
