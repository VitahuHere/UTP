package UTP8.zad1;

import UTP8.zad1.gui.MainFrame;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection connection;
    TravelData travelData;
    String url;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
    }

    public void create() {
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS offers (id INTEGER PRIMARY KEY, locale TEXT, country TEXT, dateFrom TEXT, dateTo TEXT, spot TEXT, price TEXT, currency TEXT)");
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM offers");
            int count = resultSet.getInt(1);
            if (count < travelData.getOffers().size()){
                for (Offer offer : new ArrayList<>(travelData.getOffers().subList(count, travelData.getOffers().size()))) {
                    // insert data from TravelData object into sqlite database
                    statement.execute(
                            "INSERT INTO offers (locale, country, dateFrom, dateTo, spot, price, currency) "
                                    + "VALUES ('" + offer.locale + "', '" + offer.country + "', '" + offer.dateFrom + "', '" + offer.dateTo + "', '" + offer.spot + "', '" + offer.price + "', '" + offer.currency + "')"
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void showGui() {
        ArrayList<Offer> offers = new ArrayList<>();
        Statement statement;
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM offers");
            while (resultSet.next()) {
                offers.add(new Offer(resultSet.getString("locale"), resultSet.getString("country"), resultSet.getString("dateFrom"), resultSet.getString("dateTo"), resultSet.getString("spot"), resultSet.getString("price"), resultSet.getString("currency")));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        new MainFrame(offers);
    }
}
