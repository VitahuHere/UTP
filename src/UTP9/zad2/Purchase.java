/**
 * @author Vu Cong Minh S25206
 */

package UTP9.zad2;


import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

public class Purchase implements Serializable {
    private String prod;
    private String data;
    private double price;
    private final VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public Purchase(){
        this.prod = "";
        this.data = "";
        this.price = 0.0;
    }

    public Purchase(String prod, String data, double price) {
        this.prod = prod;
        this.data = data;
        this.price = price;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String item) {
        this.prod = item;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) throws PropertyVetoException {
        propertyChangeSupport.firePropertyChange("data", this.data, data);
        this.data = data;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws PropertyVetoException {
        vetoableChangeSupport.fireVetoableChange("price", this.price, price);
        propertyChangeSupport.firePropertyChange("price", this.price, price);
        this.price = price;
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        return "Purchase [" +
                "prod=" + prod +
                ", data=" + data +
                ", price=" + price +
                ']';
    }
}
