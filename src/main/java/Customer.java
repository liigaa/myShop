public class Customer {
    private String name;
    private String email;
    private String password;
    private double balance;

    public Customer() {
    }

    public Customer(String name, String email, String password, double balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }
    public void addBalance (double balanceAdd){
        this.balance += balanceAdd;
    }



    public Boolean verifyPassword(String password){
        if(password.equals(this.password)){
            return true;
        }else {
            return false;
        }
    }
    public void reduceBalance(double payPrice){
        this.balance -= payPrice;
    }

    @Override
    public String toString() {
        return  "name: " + name  +
                ", email: " + email  +
                ", balance: " + balance;
    }
}
