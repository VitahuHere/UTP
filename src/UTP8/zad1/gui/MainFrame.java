package UTP8.zad1.gui;

import UTP8.zad1.Offer;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    public MainFrame(ArrayList<Offer> offers) {
        super("Travel Agency");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        JComboBox<String> localeComboBox = new JComboBox<>(new String[]{"en_US", "pl_PL"});
        TableModel tableModel = new OffersTableModel(offers, localeComboBox);
        JTable table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.getColumnModel().getColumn(6).setPreferredWidth(300);
        localeComboBox.addActionListener(e -> table.repaint());
        this.add(new JScrollPane(table));
        this.add(localeComboBox, "North");
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
