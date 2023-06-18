package Controller;

import Model.CarteLibrarie;
import Model.CarteLibrariePersistenta;
import Model.LanguageObservable;
import View.AngajatGui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AngajatController {
    private final AngajatGui angajatView;
    private final CarteLibrariePersistenta persistentaCarteLibrarie;
    private final LanguageObservable languageObservable;


    public AngajatController() {
        this.angajatView = new AngajatGui();
        this.persistentaCarteLibrarie = new CarteLibrariePersistenta();
        this.languageObservable = new LanguageObservable();


        JButton btnRefresh = angajatView.getBtnRefresh();
        btnRefresh.addActionListener(e -> adaugareTabel());

        JButton btnCauta = angajatView.getBtnCauta();
        btnCauta.addActionListener(e -> readaugareTabel());

        JButton btnAdauga = angajatView.getBtnAdauga();
        btnAdauga.addActionListener(e -> {
            try {
                adaugareCarteLibrarie();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton btnSterge = angajatView.getBtnSterge();
        btnSterge.addActionListener(e -> {
            try {
                stergereCarteLibrarie();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton btnActualizeaza = angajatView.getBtnActualizeaza();
        btnActualizeaza.addActionListener(e -> {
            try {
                actualizareCarteLibrarie();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton btnRomanian = this.angajatView.getBtnRomanian();
        btnRomanian.addActionListener(e -> {
            Locale.setDefault(new Locale("ro","RO"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("ro_RO");
        });

        JButton btnEnglish = this.angajatView.getBtnEnglish();
        btnEnglish.addActionListener(e -> {
            Locale.setDefault(new Locale("en","US"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("en_US");
        });

        JButton btnFrench = this.angajatView.getBtnFrench();
        btnFrench.addActionListener(e -> {
            Locale.setDefault(new Locale("fr","FR"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("fr_FR");
        });

        JButton btnSpanish = this.angajatView.getBtnSpanish();
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
            String idlLibrarie= bundle.getString("idlLibrarie");
            String idCarte= bundle.getString("idCarte");
            String idcarteLibrarie= bundle.getString("idcarteLibrarie");
            String titlu= bundle.getString("titlu");
            String Criteriu= bundle.getString("criteriu");
            String Filtru= bundle.getString("filtru");
            String usActualizare = bundle.getString("actualizareButton");
            String usStergere = bundle.getString("stergeButton");
            String usAdauga= bundle.getString("adaugaButton");
            String AddMessage = bundle.getString("AddMessage");
            String DeleteMessage = bundle.getString("DeleteMessage");
            String UpdateMessage = bundle.getString("UpdateMessage");

            String[] criteriuOptions = {
                    bundle.getString("domeniuOption"),
                    bundle.getString("autorOption"),
                    bundle.getString("pretOption"),
                    bundle.getString("disponibilOption"),
                    bundle.getString("edituraOption")
            };

            DefaultTableModel model = (DefaultTableModel) angajatView.getTable().getModel();
            model.setColumnIdentifiers(new Object[] {idlLibrarie, idcarteLibrarie,  bundle.getString("domeniuOption"), titlu,  bundle.getString("autorOption"), bundle.getString("pretOption"), bundle.getString("disponibilOption"), bundle.getString("edituraOption")});

            angajatView.setCriteriuLabels(Criteriu, criteriuOptions);
            angajatView.getBtnCauta().setText(usCauta);
            angajatView.getBtnRefresh().setText(usRefresh);
            angajatView.getLblIdLibrarie().setText(idlLibrarie);
            angajatView.getLblIdCarte().setText(idCarte);
            angajatView.getLblIdCarteLibrarie().setText(idcarteLibrarie);
            angajatView.getLblDomeniu().setText(bundle.getString("domeniuOption"));
            angajatView.getLblTitlu().setText(titlu);
            angajatView.getLblAutor().setText(bundle.getString("autorOption"));
            angajatView.getLblPret().setText(bundle.getString("pretOption"));
            angajatView.getLblDisponibil().setText(bundle.getString("disponibilOption"));
            angajatView.getLblEditura().setText(bundle.getString("edituraOption"));
            angajatView.getBtnAdauga().setText(usAdauga);
            angajatView.getBtnActualizeaza().setText(usActualizare);
            angajatView.getBtnSterge().setText(usStergere);
            angajatView.getLblCriteriu().setText(Criteriu);
            angajatView.getLblFiltru().setText(Filtru);
            angajatView.getAddMessage().setText(AddMessage);
            angajatView.getDeleteMessage().setText(DeleteMessage);
            angajatView.getUpdateMessage().setText(UpdateMessage);

        });
    }
    public void adaugareTabel() {
        List<CarteLibrarie> cartil = persistentaCarteLibrarie.selectAllCarti(Integer.parseInt(angajatView.gettxtIdLibrarie().getText()));
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

        angajatView.setTable(model);
    }

    public void readaugareTabel(){
        JComboBox<String> selectedComboBox = angajatView.getCriteriu();
        String selectedValue = selectedComboBox.getSelectedItem().toString();

        List<CarteLibrarie> cartil = persistentaCarteLibrarie.filterAllCarti(selectedValue, angajatView.gettxtFiltru().getText(), Integer.parseInt(angajatView.gettxtIdLibrarie().getText()));
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

        angajatView.setTable(model);
    }
    public void adaugareCarteLibrarie() throws SQLException {
        int idCarte = Integer.parseInt(angajatView.gettxtIdCarte().getText());
        String domeniu = angajatView.gettxtDomeniu().getText();
        String titlu = angajatView.gettxtTitlu().getText();
        String autor = angajatView.gettxtAutor().getText();
        int idLibrarie = Integer.parseInt(angajatView.gettxtIdLibrarie().getText());
        int idCarteLibrarie = Integer.parseInt(angajatView.gettxtIdCarteLibrarie().getText());
        float pret = Float.parseFloat(angajatView.gettxtPret().getText());
        int disponibil = Integer.parseInt(angajatView.gettxtDisponibil().getText());
        String editura = angajatView.gettxtEditura().getText();

        CarteLibrarie carte = new CarteLibrarie(pret, disponibil, idCarteLibrarie, editura, idLibrarie, idCarte, domeniu, titlu, autor);
        boolean ok = persistentaCarteLibrarie.adaugareCarteLibrarie(carte);
        if (ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"), angajatView.getAddMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void stergereCarteLibrarie() throws SQLException {
        boolean ok;
        int idcartelibrarie = Integer.parseInt(angajatView.gettxtIdCarteLibrarie().getText());
        ok = persistentaCarteLibrarie.stergereCarteLibrarie(idcartelibrarie);
        if(ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),angajatView.getDeleteMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void actualizareCarteLibrarie() throws SQLException {
        int idCarte = Integer.parseInt(angajatView.gettxtIdCarte().getText());
        String domeniu = angajatView.gettxtDomeniu().getText();
        String titlu = angajatView.gettxtTitlu().getText();
        String autor = angajatView.gettxtAutor().getText();
        int idLibrarie = Integer.parseInt(angajatView.gettxtIdLibrarie().getText());
        int idCarteLibrarie = Integer.parseInt(angajatView.gettxtIdCarteLibrarie().getText());
        float pret = Float.parseFloat(angajatView.gettxtPret().getText());
        int disponibil = Integer.parseInt(angajatView.gettxtDisponibil().getText());
        String editura = angajatView.gettxtEditura().getText();

        CarteLibrarie carte = new CarteLibrarie(pret, disponibil, idCarteLibrarie, editura, idLibrarie, idCarte, domeniu, titlu, autor);
        boolean ok = persistentaCarteLibrarie.actualizareCarteLibrarie(carte);
        if (ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"), angajatView.getUpdateMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void showAngajatGUI() {
        angajatView.setVisible(true);
    }
}

