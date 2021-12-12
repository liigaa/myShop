import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class Shop {
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    private ArrayList<Product> productList;
    private ArrayList<Product> customerList;
    private final DecimalFormat format = new DecimalFormat("0.00");

    public Shop(){
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.productList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }



    public String addProduct(){
        String name = JOptionPane.showInputDialog(null, "Enter product name", "Product name", JOptionPane.QUESTION_MESSAGE);
        double price = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter product price", "Product price", JOptionPane.QUESTION_MESSAGE));
        double quantity = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter product quantity", "Product quantity", JOptionPane.QUESTION_MESSAGE));
        String [] availableMeasurements = {"KG", "Item", "Liters"};
        String measurement = (String) JOptionPane.showInputDialog(
                null,
                "Enter product measurement",
                "Product Measurement",
                JOptionPane.QUESTION_MESSAGE,
                null,
                availableMeasurements,
                availableMeasurements[1]);
        Product product = new Product(name, price, quantity, measurement);

        this.products.add(product);
        return name + " added successfully";
    }

    public String addCustomer(){
        String name = JOptionPane.showInputDialog(null, "Enter name", "Customer name", JOptionPane.QUESTION_MESSAGE);
        String email = JOptionPane.showInputDialog(null, "Enter email", "Customer email", JOptionPane.QUESTION_MESSAGE);
        String password = JOptionPane.showInputDialog(null, "Enter password", "Customer password", JOptionPane.QUESTION_MESSAGE);
        double balance = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter balance", "Customer balance", JOptionPane.QUESTION_MESSAGE));
        Customer customer = new Customer(name, email, password, balance);
        this.customers.add(customer);
        return name + " add successfully";
    }


    public Customer findCustomerByEmail(String email) {
        for(Customer customer: customers){
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }return null;
    }

    public Product findProduct(String name){
        for(Product product: products){
            if(name.equalsIgnoreCase(product.getName())){
                return product;
            }
        } return null;
    }


    public Object sellProduct(Product product, Customer currentCustomer, double boughtQuantity){
        if(product.getQuantity() !=0){
            if(boughtQuantity <= product.getQuantity()){
                double payPrice = product.getPrice() * boughtQuantity;
            if(currentCustomer.getBalance()>= payPrice){
                currentCustomer.reduceBalance(payPrice);
                product.removeQuantity(boughtQuantity);
                Product productList = new Product(product.getName(), payPrice, boughtQuantity, product.getMeasurement());
                this.productList.add(productList);
                JOptionPane.showMessageDialog(null, "You have bought " + this.format.format(boughtQuantity) + "  " + product.getMeasurement() + " of " + product.getName() + " and payed " + this.format.format(payPrice) + " EUR");
            }else {
                JOptionPane.showMessageDialog(null, "Not enough money to buy product");
            }
            }else {
                JOptionPane.showMessageDialog(null, "Not enough quantity to buy, choose lesser quantity");
            }
        }else {
            JOptionPane.showMessageDialog(null, "Product is sold out");
        }
        return null;
    }
}
