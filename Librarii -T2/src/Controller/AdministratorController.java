package Controller;

import Model.Utilizator;
import Model.UtilizatorPersistenta;
import View.AdministratorGui;
import Model.LanguageObservable;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class AdministratorController {
    private final AdministratorGui administratorView;
    private final UtilizatorPersistenta utilizatorPersistenta;
    private final LanguageObservable languageObservable;


    public AdministratorController() {

        this.administratorView = new AdministratorGui();
        this.utilizatorPersistenta = new UtilizatorPersistenta();
        this.languageObservable = new LanguageObservable();


        JButton btnRefresh = administratorView.getBtnRefresh();
        btnRefresh.addActionListener(e -> adaugareTabel());

        JButton btnAdauga = administratorView.getBtnAdauga();
        btnAdauga.addActionListener(e -> {
            try {
                adaugareUtilizator();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton btnSterge = administratorView.getBtnSterge();
        btnSterge.addActionListener(e -> {
            try {
                stergereUtilizator();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton btnActualizeaza = administratorView.getBtnActualizeaza();
        btnActualizeaza.addActionListener(e -> {
            try {
                actualizareUtilizator();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton btnRomanian = this.administratorView.getBtnRomanian();
        btnRomanian.addActionListener(e -> {
            Locale.setDefault(new Locale("ro","RO"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("ro_RO");
        });

        JButton btnEnglish = this.administratorView.getBtnEnglish();
        btnEnglish.addActionListener(e -> {
            Locale.setDefault(new Locale("en","US"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("en_US");
        });

        JButton btnFrench = this.administratorView.getBtnFrench();
        btnFrench.addActionListener(e -> {
            Locale.setDefault(new Locale("fr","FR"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("fr_FR");
        });

        JButton btnSpanish = this.administratorView.getBtnSpanish();
        btnSpanish.addActionListener(e -> {
            Locale.setDefault(new Locale("es","ES"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("es_ES");
        });

        languageObservable.addObserver((observable, arg) -> {
            String language = (String) arg;
            ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle", new Locale(language.substring(0, 2), language.substring(4)));
            String usAdauga= bundle.getString("adaugaButton");
            String usnumeUtilizator= bundle.getString("numeUtilizator");
            String parola= bundle.getString("parola");
            String tip= bundle.getString("tip");
            String idUtilizator= bundle.getString("idUtilizator");
            String idLibrarie= bundle.getString("idLibrarie");
            String idlLibrarie= bundle.getString("idlLibrarie");
            String usActualizare = bundle.getString("actualizareButton");
            String usStergere = bundle.getString("stergeButton");
            String usRefresh = bundle.getString("refreshButton");
            String AddMessage = bundle.getString("AddMessage");
            String DeleteMessage = bundle.getString("DeleteMessage");
            String UpdateMessage = bundle.getString("UpdateMessage");

            DefaultTableModel model = (DefaultTableModel) administratorView.getTable().getModel();
            model.setColumnIdentifiers(new Object[] {idUtilizator, usnumeUtilizator, parola, tip, idlLibrarie});

            administratorView.getBtnAdauga().setText(usAdauga);
            administratorView.getLblUsername().setText(usnumeUtilizator);
            administratorView.getLblParola().setText(parola);
            administratorView.getLblTip().setText(tip);
            administratorView.getLblIdUtilizator().setText(idUtilizator);
            administratorView.getLblIdLibrarie().setText(idLibrarie);
            administratorView.getBtnSterge().setText(usStergere);
            administratorView.getBtnActualizeaza().setText(usActualizare);
            administratorView.getBtnRefresh().setText(usRefresh);
            administratorView.getAddMessage().setText(AddMessage);
            administratorView.getDeleteMessage().setText(DeleteMessage);
            administratorView.getUpdateMessage().setText(UpdateMessage);

        });

    }

    public void adaugareTabel() {
        List<Utilizator> utilizatori = utilizatorPersistenta.totiUtilizatorii();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5;
            }
        };
        model.addColumn("IdUtilizator");
        model.addColumn("Username");
        model.addColumn("Parola");
        model.addColumn("Tip");
        model.addColumn("IdLibrarie");


        for (Utilizator u : utilizatori) {
            model.addRow(new Object[]{u.getIdUtilizator(), u.getUser(), u.getParola(), u.getTip(), u.getIdLibrarie()});
        }

        administratorView.setTable(model);
    }

    public void adaugareUtilizator() throws SQLException {
        boolean ok;
        int idUtilizator = Integer.parseInt(String.valueOf(administratorView.gettxtIdUtilizator().getText()));
        String username = administratorView.gettxtUsername().getText();
        String parola = administratorView.gettxtPassword().getText();
        String tip = administratorView.gettxtRole().getText();
        int idLibrarie = Integer.parseInt(administratorView.gettxtidLibrarie().getText());
        Utilizator utl = new Utilizator(idUtilizator, username, parola, tip, idLibrarie);

        ok = utilizatorPersistenta.adaugareUtilizator(utl);

        if (ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"), administratorView.getAddMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void stergereUtilizator() throws SQLException {
        boolean ok;
        ok = utilizatorPersistenta.stergereUtilizator(Integer.parseInt(administratorView.gettxtIdUtilizator().getText()));
        if (ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"), administratorView.getDeleteMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void actualizareUtilizator() throws SQLException {
        boolean ok;
        int idUtilizator = Integer.parseInt(administratorView.gettxtIdUtilizator().getText());
        String username = administratorView.gettxtUsername().getText();
        String parola = administratorView.gettxtPassword().getText();
        String tip = administratorView.gettxtRole().getText();
        int idLibrarie = Integer.parseInt(administratorView.gettxtidLibrarie().getText());
        Utilizator utl = new Utilizator(idUtilizator, username, parola, tip, idLibrarie);

        ok = utilizatorPersistenta.actualizareUtilizator(utl);
        if(ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),administratorView.getUpdateMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void showAdministratorGUI() {
        adaugareTabel();
        administratorView.setVisible(true);
    }
}

