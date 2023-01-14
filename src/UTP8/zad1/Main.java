/**
 *
 *  @author Vu Cong Minh S25206
 *
 */

package UTP8.zad1;


import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    File dataDir = new File("data");
    TravelData travelData = new TravelData(dataDir);
    String dateFormat = "yyyy-MM-dd";
    for (String locale : Arrays.asList("pl_PL", "en_GB")) {
      List<String> odlist = travelData.getOffersDescriptionsList(locale, dateFormat);
      for (String od : odlist) System.out.println(od);
    }
    // --- część bazodanowa
    String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\UTP8\\zad1\\" + "offers.sqlite";
    Database db = new Database(url, travelData);
    db.create();
    db.showGui();
  }

}
