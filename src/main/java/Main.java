import javax.swing.*;
import java.text.DecimalFormat;

public class Main {
    Shop roko = new Shop();
   private final DecimalFormat format = new DecimalFormat("0.00");

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    void menu() {
        String option;

        do {
            String[] options = {"Select...", "Shop manager", "Customer register", "Customer Log in","Exit"};

            option = (String) JOptionPane.showInputDialog(null, """
                            Welcome to ROKO shop!
                            Please select your profile
                            Choose EXIT to quit""",
                    "Shop menu",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
            switch (option) {
                case "Shop manager":
                    shopManager();
                    break;
                case "Customer Log in":
                    customerMenu();
                    break;
                case "Customer register":
                    JOptionPane.showMessageDialog(null, roko.addCustomer());
                    break;
                default:
                    break;
            }
        } while (!option.equals("Exit"));
    }
        void shopManager(){
            String option;
            do {
                String[] options = {"Select...", "Add product", "Update product", "Add customer", "View all customers", "View all products", "View sales history", "Exit"};

                option = (String) JOptionPane.showInputDialog(null, """
                                Welcome back!
                                Please select your task
                                Choose EXIT to quit""",
                        "Manager menu",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                switch (option) {
                    case "Add product":
                        JOptionPane.showMessageDialog(null, roko.addProduct());
                        break;
                    case "Update product":
                        JOptionPane.showMessageDialog(null, updateProduct());
                        break;
                    case "Add customer":
                        JOptionPane.showMessageDialog(null, roko.addCustomer());
                        break;
                    case "View all products":
                        JOptionPane.showMessageDialog(null, roko.getProducts(), "All products", JOptionPane.INFORMATION_MESSAGE,null);
                        break;
                    case "View all customers":
                        JOptionPane.showMessageDialog(null, roko.getCustomers(), "All customers", JOptionPane.INFORMATION_MESSAGE, null);
                        break;
                    case "View sales history":
                        JOptionPane.showMessageDialog(null,  roko.getProductList(), "Sale history", JOptionPane.INFORMATION_MESSAGE, null);
                        break;

                    default:
                        break;
                }
            } while (!option.equals("Exit"));
        }
        void customerMenu(){
        Customer currentCustomer;
        currentCustomer = signIn();

        if(currentCustomer != null){

        String option;
            do {
                String[] options = {"Select...", "View all products", "View balance","Buy products" , "Update balance",  "Exit"};

                option = (String) JOptionPane.showInputDialog(null, "Welcome " + currentCustomer.getName() + "!" +
                                "\nPlease select options" +
                                "\nChoose EXIT to quit",
                        "Customer menu",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                switch (option) {
                    case "View balance":
                        JOptionPane.showMessageDialog(null, "Your balance is: " + this.format.format(currentCustomer.getBalance()) + " EUR", "Balance", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "Update balance":
                        JOptionPane.showMessageDialog(null, addBalance(currentCustomer), "Balance update", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "View all products":
                        JOptionPane.showMessageDialog(null, roko.getProducts(), "All products", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "Buy products":
                        JOptionPane.showMessageDialog(null, buyProducts(currentCustomer));
                        break;
                    default:
                        break;
                }
            } while (!option.equals("Exit"));
        }}

    Customer signIn(){
        String email = JOptionPane.showInputDialog(null, "Please Log in! Enter email", "Log in", JOptionPane.INFORMATION_MESSAGE);
        Customer currentCustomer = roko.findCustomerByEmail(email);
        String password = JOptionPane.showInputDialog(null,"Please enter password", "Log in", JOptionPane.INFORMATION_MESSAGE);
        if(currentCustomer !=null){
            if(currentCustomer.verifyPassword(password)){
                return currentCustomer;
            }
        } return null;
    }
    String addBalance(Customer currentCustomer){
        double newBalance = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the Balance you added", "Update balance", JOptionPane.QUESTION_MESSAGE));
        currentCustomer.addBalance(newBalance);
        return "Your balance is: " + this.format.format(currentCustomer.getBalance()) + " EUR";
    }

    Object buyProducts(Customer currentCustomer){
        Product productToBuy = (Product) JOptionPane.showInputDialog(
                null,
                "Choose product",
                "Buy product",
                JOptionPane.QUESTION_MESSAGE,
                null,
                this.roko.getProducts().toArray(new Product[0]),
                this.roko.getProductList()
                );
        Product product = roko.findProduct(productToBuy.getName());
        double quantity = Double.parseDouble(JOptionPane.showInputDialog("Enter product amount what want to buy"));
        JOptionPane.showMessageDialog(null, roko.sellProduct(product, currentCustomer, quantity));
        return null;
    }
     Object updateProduct(){
        Product productToUpdate = (Product) JOptionPane.showInputDialog(null,
                "Choose product",
                "Update product",
                JOptionPane.QUESTION_MESSAGE,
                null,
                this.roko.getProducts().toArray(new Product[0]),
                this.roko.getProducts());
        Product product = roko.findProduct(productToUpdate.getName());
        double quantity = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter product amount what want to add", "Update quantity", JOptionPane.QUESTION_MESSAGE));
        double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter product new price", "Update price", JOptionPane.QUESTION_MESSAGE));
        product.addQuantity(quantity);
        product.price = price;
         return null;
     }

    }





