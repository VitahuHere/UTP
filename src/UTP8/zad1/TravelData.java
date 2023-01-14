package UTP8.zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TravelData {
    private final ArrayList<Offer> offers;
    public TravelData(File dataDir) {
        offers = new ArrayList<>();
        File[] files = dataDir.listFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    List<String> lines = Files.readAllLines(file.toPath());
                    for (String line : lines) {
                        String[] parts = line.split("\t");
                        if (parts.length == 7) {
                            offers.add(new Offer(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        ArrayList<String> descriptions = new ArrayList<>();
        for (Offer offer : offers) {
            descriptions.add(offer.getDescription(locale, dateFormat));
        }
        return descriptions;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }
}
