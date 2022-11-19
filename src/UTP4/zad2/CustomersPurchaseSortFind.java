/**
 *
 *  @author Vu Cong Minh S25206
 *
 */

package UTP4.zad2;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {
    List<Purchase> data;

    public CustomersPurchaseSortFind() {
        data = new ArrayList<>();
    }

    public void readFile(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for(String line : lines) {
                data.add(new Purchase(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortBySurname() {
        List<Purchase> sortedData = new ArrayList<>(data);
        sortedData.sort(Comparator
                .comparing(Purchase::getFullName)
                .thenComparing(Purchase::getId)
        );
        for(Purchase purchase : sortedData) {
            System.out.println(purchase);
        }
    }

    private void sortByPurchase(){
        List<Purchase> sortedData = new ArrayList<>(data);
        sortedData.sort(Comparator
                .comparingDouble(Purchase::getFullPurchasePrice)
                .reversed()
                .thenComparing(Purchase::getId)
        );
        for(Purchase purchase : sortedData) {
            System.out.println(purchase + " (koszt: " + purchase.getFullPurchasePrice() + ")");
        }
    }

    public void showSortedBy(String sortType) {
        System.out.println(sortType);
        switch (sortType) {
            case "Nazwiska":
                sortBySurname();
                break;
            case "Koszty":
                sortByPurchase();
                break;
        }
        System.out.println();
    }

    public void showPurchaseFor(String customerId) {
        System.out.println("Klient " + customerId);
        for(Purchase purchase : data) {
            if(purchase.getId().equals(customerId)) {
                System.out.println(purchase);
            }
        }
        System.out.println();
    }
}
