package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManagerGui extends JFrame {
    private final JTextField txtFiltru = new JTextField();
    String[] options = {"Domeniu", "Disponibil", "Editura", "Autor", "Pret"};
    JComboBox<String> comboCriteriu = new JComboBox<>(options);

    private final JTable table = new JTable();
    private final JScrollPane scrollPane = new JScrollPane();
    private final JTextField txtIdLibrarie = new JTextField();
    JButton btnCauta;
    JButton btnRefresh;
    JButton btnCuratare;
    JLabel lblIdLibrarie;
    JLabel lblCriteriu;
    JLabel lblFiltru;
    JButton btnEnglish;
    JButton btnRomanian;
    JButton btnSpanish;
    JButton btnFrench;

    public ManagerGui() {
        JPanel panelManager = new JPanel();
        setContentPane(panelManager);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnCauta = new JButton("Cauta");
        btnRefresh = new JButton("Reimprospateaza");
        btnCuratare = new JButton("Curatare");

        ImageIcon image = new ImageIcon("src/resurse/poze.png");
        btnEnglish = new JButton();
        btnEnglish.setIcon(image);
        btnEnglish.setBorderPainted(false);

        ImageIcon French = new ImageIcon("src/resurse/France.png");
        btnFrench = new JButton();
        btnFrench.setIcon(French);
        btnFrench.setBorderPainted(false);

        ImageIcon Romanian = new ImageIcon("src/resurse/Romania.png");
        btnRomanian = new JButton();
        btnRomanian.setIcon(Romanian);
        btnRomanian.setBorderPainted(false);

        ImageIcon Spanish = new ImageIcon("src/resurse/Spain.png");
        btnSpanish = new JButton();
        btnSpanish.setIcon(Spanish);
        btnSpanish.setBorderPainted(false);

        panelManager.setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel();
        lblIdLibrarie = new JLabel("IdLibrarie:");
        filterPanel.add(lblIdLibrarie);
        txtIdLibrarie.setPreferredSize(new Dimension( 80, 25));
        filterPanel.add(txtIdLibrarie);
        lblCriteriu = new JLabel("Criteriu:");
        filterPanel.add(lblCriteriu);
        filterPanel.add(comboCriteriu);
        lblFiltru = new JLabel("Filtru:");
        filterPanel.add(lblFiltru);
        txtFiltru.setPreferredSize(new Dimension( 80, 25)); // setam dimensiunea campului text
        filterPanel.add(txtFiltru);
        filterPanel.add(btnCauta);
        filterPanel.add(btnCuratare);
        filterPanel.setBackground(new Color(237, 245, 152));
        panelManager.add(filterPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        panelManager.add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnEnglish);
        buttonPanel.add(btnFrench);
        buttonPanel.add(btnSpanish);
        buttonPanel.add(btnRomanian);
        buttonPanel.setBackground(new Color(237, 245, 152));
        panelManager.add(buttonPanel, BorderLayout.SOUTH);
    }
    public JComboBox<String> getCriteriu() {
        return comboCriteriu;
    }

    public JTextField gettxtFiltru(){return txtFiltru;}
    public JButton getBtnRefresh() {
        return btnRefresh;
    }
    public JButton getBtnCauta() {
        return btnCauta;
    }
    public JButton getBtnCuratare() {
        return btnCuratare;
    }
    public JTextField gettxtIdLibrarie() {return txtIdLibrarie;}
    public JButton getBtnRomanian() {return btnRomanian;}
    public JButton getBtnFrench() {return btnFrench;}
    public JButton getBtnEnglish() {return btnEnglish;}
    public JButton getBtnSpanish() {return btnSpanish;}

    public JLabel getLblIdLibrarie() {return lblIdLibrarie;}
    public JLabel getLblCriteriu() {return lblCriteriu;}
    public JLabel getLblFiltru() {return lblFiltru;}

    public JTable getTable(){return table;}
    public void setCriteriuLabels(String label, String[] options) {
        lblCriteriu.setText(label);
        comboCriteriu.setModel(new DefaultComboBoxModel<>(options));
    }
    public void setTable(DefaultTableModel model) {
        table.setModel(model);
        scrollPane.setViewportView(table);
    }
}
