/**
 * @author Vu Cong Minh S25206
 */

package UTP4.zad2;


import UTP4.zad2.CustomersPurchaseSortFind;

public class Main {

    public static void main(String[] args) {
        CustomersPurchaseSortFind cpsf = new CustomersPurchaseSortFind();
        String fname = System.getProperty("user.home") + "/customers.txt";
        cpsf.readFile(fname);
        cpsf.showSortedBy("Nazwiska");
        cpsf.showSortedBy("Koszty");

        String[] custSearch = {"c00001", "c00002"};

        for (String id : custSearch) {
            cpsf.showPurchaseFor(id);
        }
    }

}
