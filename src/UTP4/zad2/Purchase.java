/**
 * @author Vu Cong Minh S25206
 */

package UTP4.zad2;


public class Purchase {
    private final String id;
    private final String fullName;
    private final String productName;
    private final double price;
    private final double quantity;

    public Purchase(String dataLine) {
        String[] data = dataLine.split(";");
        if (data.length != 5) {
            throw new IllegalArgumentException("Wrong data format");
        }
        this.id = data[0];
        this.fullName = data[1];
        this.productName = data[2];
        this.price = Double.parseDouble(data[3]);
        this.quantity = Double.parseDouble(data[4]);
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public double getFullPurchasePrice() {
        return price * quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return id + ";" + fullName + ";" + productName + ";" + price + ";" + quantity;
    }
}
