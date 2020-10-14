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
    private HashMap<Integer, HashMap<String, Integer>> outage_records = new HashMap<>();
    
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

    public int get_outage_count() {
        int total_count = 0;
        for (int day : outage_records.keySet() ) {
            for (String customer : outage_records.get(day).keySet()) {
                total_count += outage_records.get(day).get(customer);
            }
        }

        return total_count;
    }

    public void add_outage_record(String customer_type, int timestamp){
        HashMap<String, Integer> value = null;

        // check whether the key exists
        if ( !outage_records.containsKey(timestamp) ) {
            value = new HashMap<>();
            outage_records.put(timestamp, value);
        } else {
            value = outage_records.get(timestamp);
        }
        
        // add customer into value hashmap
        if ( !value.containsKey(customer_type) ) {
            value.put(customer_type, 1);
        } else {
            value.replace(customer_type, value.get(customer_type) + 1);
        }
    }

    public HashMap<Integer, ArrayList<RecordInfo>> get_record() {
        return this.records;
    }

    public void summary(int day) {
        HashMap<String, Integer> sum_sell_by_roll = new HashMap<>();
        HashMap<String, Float> sum_pay_by_customer = new HashMap<>();
        HashMap<String, Integer> sum_outage_by_customer = new HashMap<>();

        this.generate_summary(sum_sell_by_roll, sum_pay_by_customer, sum_outage_by_customer, day);
        this.display_summary(sum_sell_by_roll, sum_pay_by_customer, sum_outage_by_customer);
    }

    public void summary_all(int total_days) {
        HashMap<String, Integer> sum_sell_by_roll = new HashMap<>();
        HashMap<String, Float> sum_pay_by_customer = new HashMap<>();
        HashMap<String, Integer> sum_outage_by_customer = new HashMap<>();

        for (int day = 1; day <= total_days; day ++) {
            this.generate_summary(sum_sell_by_roll, sum_pay_by_customer, sum_outage_by_customer, day);
        }

        this.display_summary(sum_sell_by_roll, sum_pay_by_customer, sum_outage_by_customer);
    }

    private void generate_summary(HashMap<String, Integer> sum_sell_by_roll, HashMap<String, Float> sum_pay_by_customer, HashMap<String, Integer> sum_outage_by_customer, int day) {

        for (RecordInfo record_info : records.get(day)) {
            String customer = record_info.customer_type;
            String product = record_info.product_type;
            float price = record_info.price;

            if ( !sum_pay_by_customer.containsKey(customer) ) sum_pay_by_customer.put(customer, price);
            else sum_pay_by_customer.replace(customer, sum_pay_by_customer.get(customer) + price);

            if ( !sum_sell_by_roll.containsKey(product) ) sum_sell_by_roll.put(product, 1);
            else sum_sell_by_roll.replace(product, sum_sell_by_roll.get(product) + 1);
        }

        if ( outage_records.get(day) == null || outage_records.get(day).keySet() == null ) return;
        for (String customer : outage_records.get(day).keySet()) {
            int count = outage_records.get(day).get(customer);
            if ( !sum_outage_by_customer.containsKey(customer) ) sum_outage_by_customer.put(customer, count);
            else sum_outage_by_customer.replace(customer, sum_outage_by_customer.get(customer) + count);
        }
    }

    private void display_summary(HashMap<String, Integer> sum_sell_by_roll, HashMap<String, Float> sum_pay_by_customer, HashMap<String, Integer> sum_outage_by_customer) {
        System.out.println("\u001B[34m----> Count inventory orders by roll type <----");
        for ( String key : sum_sell_by_roll.keySet() ) {
            System.out.println(String.format("Product: %s   Total sell: %d", key, sum_sell_by_roll.get(key)));
        }

        System.out.println("\n----> Total payment for orders by customer type <----");
        float total_bill = 0.0f;
        for ( String key : sum_pay_by_customer.keySet() ) {
            float curr_bill = sum_pay_by_customer.get(key);
            total_bill += curr_bill;
            System.out.println(String.format("Customer: %s   Total bill: %f", key, curr_bill));
        }
        System.out.println(String.format("Total payment: %f", total_bill));

        if ( sum_outage_by_customer.size() == 0 ) return;
        int total_outage = 0;
        System.out.println("\n----> Orders impacted by a roll outage by customer type <----");
        for (String customer : sum_outage_by_customer.keySet()) {
            int curr_outage = sum_outage_by_customer.get(customer);
            total_outage += curr_outage;
            System.out.println(String.format("Customer: %s   Outage: %d", customer, curr_outage));
        }
        System.out.println(String.format("Total outage: %d", total_outage));
    }
}
