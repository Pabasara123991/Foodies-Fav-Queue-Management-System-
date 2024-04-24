public class FoodQueue {
    private Customer[]customers;
    private int size;

    public FoodQueue(int size) {
        this.size = size;
        customers = new Customer[size];
    }

    public Customer[] getCustomer() {
        return customers;
    }
}
