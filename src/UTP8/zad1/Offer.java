/**
 * @author Vu Cong Minh S25206
 */

package UTP8.zad1;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class Offer {
    Locale loc;
    final Locale locale;
    final String country;
    final Date dateFrom;
    final Date dateTo;
    final String spot;
    final Currency currency;
    private final boolean isCountryDefault;
    private final boolean isSpotDefault;
    BigDecimal price;

    public Offer(String locale, String country, String dateFrom, String dateTo, String spot, String price, String currency) {
        String[] parts = locale.split("_");
        Locale loc;
        if (parts.length == 2) {
            loc = new Locale.Builder().setLanguage(parts[0]).setRegion(parts[1]).build();
        } else {
            loc = new Locale.Builder().setLanguage(parts[0]).build();
        }
        this.locale = loc;
        this.dateFrom = Date.valueOf(dateFrom);
        this.dateTo = Date.valueOf(dateTo);
        this.currency = Currency.getInstance(currency);
        try {
            NumberFormat format = NumberFormat.getInstance(loc);
            this.price = new BigDecimal(format.parse(price).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle("prop.offers", loc);
        HashMap<String, String> valueKey = new HashMap<>();
        for (String key : bundle.keySet()) {
            valueKey.put(bundle.getString(key), key);
        }
        HashMap<String, String> keyValue = new HashMap<>();
        for (String key : bundle.keySet()) {
            keyValue.put(key, bundle.getString(key));
        }
        if (valueKey.containsKey(country)) {
            this.country = valueKey.get(country);
            isCountryDefault = false;
        } else {
            if(keyValue.containsKey(country)) {
                this.country = country;
                isCountryDefault = false;
            } else {
                this.country = country;
                isCountryDefault = true;
            }
        }
        if (valueKey.containsKey(spot)) {
            this.spot = valueKey.get(spot);
            isSpotDefault = false;
        } else {
            if(keyValue.containsKey(spot)) {
                this.spot = spot;
                isSpotDefault = false;
            } else {
                this.spot = country;
                isSpotDefault = true;
            }
        }
    }

    public String getDescription(String locale, String dateFormat) {
        String[] parts = locale.split("_");
        Locale loc = new Locale.Builder().setLanguage(parts[0]).setRegion(parts[1]).build();
        String country, spot;
        this.loc = loc;
        country = this.getCountry();
        spot = this.getSpot();
        NumberFormat nf = NumberFormat.getInstance(loc);
        nf.setCurrency(currency);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat, loc);
        return String.format("%s %s %s %s %s %s", country, dtf.format(dateFrom.toLocalDate()), dtf.format(dateTo.toLocalDate()), spot, nf.format(price), currency);
    }

    public String getDescription() {
        return getDescription(loc.toString(), "yyyy-MM-dd");
    }

    public String getCountry() {
        if (isCountryDefault) {
            return country;
        }
        ResourceBundle bundle = ResourceBundle.getBundle("prop.offers", this.loc);
        return bundle.getString(this.country);
    }

    private String getLocalisedDate(Date date) {
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, this.loc);
        String localPattern = ((SimpleDateFormat) formatter).toLocalizedPattern();
        return date.toLocalDate().format(DateTimeFormatter.ofPattern(localPattern));
    }

    public String getDateFrom() {
        return getLocalisedDate(dateFrom);
    }

    public String getDateTo() {
        return getLocalisedDate(dateTo);
    }

    public String getSpot() {
        if (isSpotDefault) {
            return spot;
        }
        ResourceBundle bundle = ResourceBundle.getBundle("prop.offers", this.loc);
        return bundle.getString(this.spot);
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setLocale(Object locale) {
        String loc = "pl_PL";
        if (locale != null) {
            loc = (String) locale;
        }
        String[] parts = loc.split("_");
        this.loc = new Locale.Builder().setLanguage(parts[0]).setRegion(parts[1]).build();
    }
}
