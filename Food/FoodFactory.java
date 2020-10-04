package Food;

import Food.Type.EggRoll;
import Food.Type.JellyRoll;
import Food.Type.PastryRoll;
import Food.Type.SausageRoll;
import Food.Type.SpringRoll;

public class FoodFactory {

    public static final int TYPE_EGG_ROLL     = 0x0;
    public static final int TYPE_JELLY_ROLL   = 0x1;
    public static final int TYPE_PASTRY_ROLL  = 0x2;
    public static final int TYPE_SAUSAGE_ROLL = 0x3;
    public static final int TYPE_SPRING_ROLL  = 0x4;

    public Food create(int type) {
        if ( type == TYPE_EGG_ROLL ) return new EggRoll();
        if ( type == TYPE_JELLY_ROLL ) return new JellyRoll();
        if ( type == TYPE_PASTRY_ROLL ) return new PastryRoll();
        if ( type == TYPE_SAUSAGE_ROLL ) return new SausageRoll();
        if ( type == TYPE_SPRING_ROLL ) return new SpringRoll();

        return null;
    }
}
