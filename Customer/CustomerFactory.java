package Customer;

import Customer.Type.Business;
import Customer.Type.Casual;
import Customer.Type.Catering;

public class CustomerFactory {
    
    public static final int TYPE_BUSINESS = 0x0;
    public static final int TYPE_CASUAL   = 0x1;
    public static final int TYPE_CATERING = 0x2;

    public static Customer create(int type) {
        if ( type == TYPE_BUSINESS ) return new Business();
        if ( type == TYPE_CASUAL ) return new Casual();
        if ( type == TYPE_CATERING ) return new Catering();

        return null;
    }
}
