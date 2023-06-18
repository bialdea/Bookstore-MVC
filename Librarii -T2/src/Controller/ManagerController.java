package Controller;

import Model.CarteLibrarie;
import Model.CarteLibrariePersistenta;
import Model.LanguageObservable;
import View.ManagerGui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ManagerController {
    private final ManagerGui managerView;
    private final CarteLibrariePersistenta persistentaCarteLibrarie;
    private final LanguageObservable languageObservable;

    public ManagerController(){
        this.managerView = new ManagerGui();
        this.persistentaCarteLibrarie= new CarteLibrariePersistenta();
        this.languageObservable = new LanguageObservable();
        

        JButton btnRefresh = managerView.getBtnRefresh();
        btnRefresh.addActionListener(e -> {
            adaugareTabel();
        });

        JButton btnCauta = managerView.getBtnCauta();
        btnCauta.addActionListener(e -> {
                readaugareTabel();
        });

        JButton btnRomanian = this.managerView.getBtnRomanian();
        btnRomanian.addActionListener(e -> {
            Locale.setDefault(new Locale("ro","RO"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("ro_RO");
        });

        JButton btnEnglish = this.managerView.getBtnEnglish();
        btnEnglish.addActionListener(e -> {
            Locale.setDefault(new Locale("en","US"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("en_US");
        });

        JButton btnFrench = this.managerView.getBtnFrench();
        btnFrench.addActionListener(e -> {
            Locale.setDefault(new Locale("fr","FR"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("fr_FR");
        });

        JButton btnSpanish = this.managerView.getBtnSpanish();
        btnSpanish.addActionListener(e -> {
            Locale.setDefault(new Locale("es","ES"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("es_ES");
        });

        languageObservable.addObserver((observable, arg) -> {
            String language = (String) arg;
            ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle", new Locale(language.substring(0, 2), language.substring(4)));
            String usCauta= bundle.getString("cautaButton");
            String usRefresh = bundle.getString("refreshButton");
            String usCurata = bundle.getString("curataButton");
            String idlLibrarie= bundle.getString("idlLibrarie");
            String idcarteLibrarie= bundle.getString("idcarteLibrarie");
            String titlu= bundle.getString("titlu");
            String Criteriu= bundle.getString("criteriu");
            String Filtru= bundle.getString("filtru");
            String[] criteriuOptions = {
                    bundle.getString("domeniuOption"),
                    bundle.getString("autorOption"),
                    bundle.getString("pretOption"),
                    bundle.getString("disponibilOption"),
                    bundle.getString("edituraOption")
            };

            DefaultTableModel model = (DefaultTableModel) managerView.getTable().getModel();
            model.setColumnIdentifiers(new Object[] {idlLibrarie, idcarteLibrarie,  bundle.getString("domeniuOption"), titlu,  bundle.getString("autorOption"), bundle.getString("pretOption"), bundle.getString("disponibilOption"), bundle.getString("edituraOption")});

            managerView.setCriteriuLabels(Criteriu, criteriuOptions);
            managerView.getBtnCauta().setText(usCauta);
            managerView.getBtnRefresh().setText(usRefresh);
            managerView.getBtnCuratare().setText(usCurata);
            managerView.getLblIdLibrarie().setText(idlLibrarie);
            managerView.getLblCriteriu().setText(Criteriu);
            managerView.getLblFiltru().setText(Filtru);


        });
    }
    public void adaugareTabel() {
       List<CarteLibrarie> cartil = persistentaCarteLibrarie.selectAllCarti(Integer.parseInt(managerView.gettxtIdLibrarie().getText()));
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5 && column != 6 && column != 7;
            }
        };

        model.addColumn("id_librarie");
        model.addColumn("IdCarteLibrarie");
        model.addColumn("Domeniu");
        model.addColumn("Titlu");
        model.addColumn("Autor");
        model.addColumn("Pret");
        model.addColumn("Disponibil");
        model.addColumn("Editura");

        for (CarteLibrarie cl : cartil) {
            model.addRow(new Object[]{cl.getIdLibrarie(), cl.getIdCarteLibrarie(), cl.getDomeniu(), cl.getTitlu(), cl.getAutor(), cl.getPret(), cl.getDisponibil(), cl.getEditura()});
        }

        managerView.setTable(model);
    }

    public void readaugareTabel(){
        JComboBox<String> selectedComboBox = managerView.getCriteriu();
        String selectedValue = selectedComboBox.getSelectedItem().toString();


        List<CarteLibrarie> cartil = persistentaCarteLibrarie.filterAllCarti(selectedValue, managerView.gettxtFiltru().getText(), Integer.parseInt(managerView.gettxtIdLibrarie().getText()));
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5 && column != 6 && column != 7;
            }
        };

        model.addColumn("id_librarie");
        model.addColumn("IdCarteLibrarie");
        model.addColumn("Domeniu");
        model.addColumn("Titlu");
        model.addColumn("Autor");
        model.addColumn("Pret");
        model.addColumn("Disponibil");
        model.addColumn("Editura");

        for (CarteLibrarie cl : cartil) {
            model.addRow(new Object[]{cl.getIdLibrarie(), cl.getIdCarteLibrarie(), cl.getDomeniu(), cl.getTitlu(), cl.getAutor(), cl.getPret(), cl.getDisponibil(), cl.getEditura()});
        }

        managerView.setTable(model);
    }

    public void showManagerGUI() {
        managerView.setVisible(true);
    }

}
