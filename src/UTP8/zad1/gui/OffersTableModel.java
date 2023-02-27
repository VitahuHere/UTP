/**
 * @author Vu Cong Minh S25206
 */

package UTP8.zad1.gui;

import UTP8.zad1.Offer;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class OffersTableModel implements TableModel {
    private final ArrayList<Offer> offers;
    private final JComboBox<String> localeComboBox;
    public OffersTableModel(ArrayList<Offer> offers, JComboBox<String> localeComboBox) {
        this.offers = offers;
        this.localeComboBox = localeComboBox;
    }


    @Override
    public int getRowCount() {
        return offers.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String locale = "pl_PL";
        String[] parts = localeComboBox.getSelectedItem() == null ? locale.split("_") : "pl_PL".split("_");
        Locale loc = new Locale.Builder().setLanguage(parts[0]).setRegion(parts[1]).build();
        ResourceBundle bundle = ResourceBundle.getBundle("prop.offers", loc);
        switch (columnIndex) {
            case 0:
                return bundle.getString("COUNTRY");
            case 1:
                return bundle.getString("DATE_FROM");
            case 2:
                return bundle.getString("DATE_TO");
            case 3:
                return bundle.getString("SPOT");
            case 4:
                return bundle.getString("PRICE");
            case 5:
                return bundle.getString("CURRENCY");
            case 6:
                return bundle.getString("DESCRIPTION");
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 3:
            case 6:
            case 1:
            case 2:
                return String.class;
            case 4:
                return BigDecimal.class;
            case 5:
                return Currency.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Offer offer = offers.get(rowIndex);
        offer.setLocale(this.localeComboBox.getSelectedItem());
        switch (columnIndex) {
            case 0:
                return offer.getCountry();
            case 1:
                return offer.getDateFrom();
            case 2:
                return offer.getDateTo();
            case 3:
                return offer.getSpot();
            case 4:
                return offer.getPrice();
            case 5:
                return offer.getCurrency();
            case 6:
                return offer.getDescription();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
