public class Product {
    public final String name;
    public double price;
    private double quantity;
    private final String measurement;

    public Product(String name, double price, double quantity, String measurement) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.measurement = measurement;
    }



    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void addQuantity(double quantityAdd){
        this.quantity += quantityAdd;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void removeQuantity (double quantityRemove){
        this.quantity -= quantityRemove;
    }

    @Override
    public String toString() {
        return  "\nname: " + name  +
                ", price: " + price +
                ", quantity: " + quantity +
                ", measurement: " + measurement;
    }


}
