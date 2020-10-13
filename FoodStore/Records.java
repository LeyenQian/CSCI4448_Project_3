package FoodStore;

import java.util.ArrayList;
import java.util.HashMap;

class RecordInfo {
    public String customer_type;
    public String product_type;
    float price;

    public RecordInfo(String customer_type, String product_type, float price) {
        this.customer_type = customer_type;
        this.product_type = product_type;
        this.price = price;
    }
}

public class Records {
    private HashMap<Integer, ArrayList<RecordInfo>> records = new HashMap<>();
    
    public void add_record(String customer_type, String product_type, float price, int timestamp) {
        ArrayList<RecordInfo> value = null;

        // check whether the key exists
        if ( !records.containsKey(timestamp) ) {
            value = new ArrayList<>();
            records.put(timestamp, value);
        } else {
            value = records.get(timestamp);
        }
        
        value.add(new RecordInfo(customer_type, product_type, price));
    }

    public void summary(int day) {
        ArrayList<RecordInfo> value = records.get(day);
        HashMap<String, Integer> sum_sell_by_roll = new HashMap<>();
        HashMap<String, Float> sum_pay_by_customer = new HashMap<>();

        for (RecordInfo record_info : value) {
            String customer = record_info.customer_type;
            String product = record_info.product_type;
            float price = record_info.price;

            if ( !sum_pay_by_customer.containsKey(customer) ) sum_pay_by_customer.put(customer, price);
            sum_pay_by_customer.replace(customer, sum_pay_by_customer.get(customer) + price);

            if ( !sum_sell_by_roll.containsKey(product) ) sum_sell_by_roll.put(product, 0);
            sum_sell_by_roll.replace(product, sum_sell_by_roll.get(product) + 1);
        }

        System.out.println("\u001B[34m----> Count inventory orders by roll type <----");
        for ( String key : sum_sell_by_roll.keySet() ) {
            System.out.println(String.format("Product: %s   Total sell: %d", key, sum_sell_by_roll.get(key)));
        }

        System.out.println("\n\u001B[35m----> Total payment for orders for the day by customer type <----");
        float total_bill = 0.0f;
        for ( String key : sum_pay_by_customer.keySet() ) {
            float curr_bill = sum_pay_by_customer.get(key);
            total_bill += curr_bill;
            System.out.println(String.format("Customer: %s   Total bill: %f", key, curr_bill));
        }
        System.out.println(String.format("Total payment: %f", total_bill));
    }
}
