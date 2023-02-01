/**
 * @author Vu Cong Minh S25206
 */

package UTP9.zad2;


import java.beans.PropertyVetoException;

public class Main {
    public static void main(String[] args) {

        Purchase purch = new Purchase("komputer", "nie ma promocji", 3000.00);
        System.out.println(purch);

        purch.addVetoableChangeListener(evt -> {
            if (evt.getPropertyName().equals("price")) {
                if ((double) evt.getNewValue() < 1000) {
                    throw new PropertyVetoException("Price change to " + evt.getNewValue() + " not allowed", evt);
                }
            }
        });

        purch.addPropertyChangeListener(evt -> System.out.println("Change value of: data from: " + evt.getOldValue() + " to: " + evt.getNewValue()));

        try {
            purch.setData("w promocji");
            purch.setPrice(2000.00);
            System.out.println(purch);

            purch.setPrice(500.00);

        } catch (PropertyVetoException exc) {
            System.out.println(exc.getMessage());
        }
        System.out.println(purch);

    }
}
