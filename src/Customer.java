public class Customer {
    private String firstName;
    private String secondName;
    private  int requiredBurgerQuantity;

    public Customer(String fName,String sName,int burger){
        this.firstName = fName;
        this.secondName = sName;
        this.requiredBurgerQuantity = burger;
    }

    public String getFull_name(){
        return firstName+" "+secondName;}

    public  int getBurgerQuantity() {
        return requiredBurgerQuantity;
    }
}
