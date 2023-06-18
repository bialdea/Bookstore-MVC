package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class AngajatGui extends JFrame{

    JButton btnAdauga;
    JButton btnActualizeaza;
    JButton btnSterge;
    JButton btnCauta;
    JButton btnRefresh;
    JButton btnEnglish;
    JButton btnRomanian;
    JButton btnSpanish;
    JButton btnFrench;

    String[] options = {"Domeniu", "Disponibil", "Editura", "Autor", "Pret"};

    JComboBox<String> comboCriteriu = new JComboBox<>(options);
    public static int id_librarie = 125;
    private final JScrollPane scrollPane = new JScrollPane();

    public final JTable table = new JTable();
    private final JTextField txtFiltru = new JTextField();

    private final JTextField txtIdLibrarie = new JTextField(Integer.toString(id_librarie));
    JLabel addMessage;
    JLabel deleteMessage;
    JLabel updateMessage;
    JTextField txtIdCarteLibrarie = new JTextField();

    JTextField txtIdCarte = new JTextField();

    JTextField txtDomeniu = new JTextField();

    JTextField txtTitlu = new JTextField();

    JTextField txtAutor = new JTextField();

    JTextField txtDisponibil = new JTextField();

    JTextField txtEditura = new JTextField();
    JTextField txtPret = new JTextField();
    JLabel lblIdLibrarie;
    JLabel lblIdCarteLibrarie;
    JLabel lblFiltru;
    JLabel lblCriteriu;
    JLabel lblIdCarte;
    JLabel lblDomeniu;
    JLabel lblTitlu;
    JLabel lblAutor;
    JLabel lblPret;
    JLabel lblDisponibil;
    JLabel lblEditura;

    public AngajatGui() {
        JPanel panelAngajat = new JPanel();
        setContentPane(panelAngajat);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnAdauga = new JButton("Adauga");
        btnActualizeaza = new JButton("Actualizeaza");
        btnSterge = new JButton("Sterge");
        btnCauta = new JButton("Cauta");
        btnRefresh = new JButton("Reimprospateaza");
        addMessage= new JLabel("Adaugare efectuata cu succes!");
        deleteMessage= new JLabel("Stergere efectuata cu succes!");
        updateMessage= new JLabel("Actualizare efectuata cu succes!");

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

        panelAngajat.setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel();

        lblCriteriu =new JLabel("Criteriu:");
        filterPanel.add(lblCriteriu);
        filterPanel.add(comboCriteriu);
        lblFiltru = new JLabel("Filtru:");
        filterPanel.add(lblFiltru);
        txtFiltru.setPreferredSize(new Dimension( 70, 25)); // setam dimensiunea campului text
        filterPanel.add(txtFiltru);
        filterPanel.add(btnCauta);
        panelAngajat.add(filterPanel, BorderLayout.WEST);
        filterPanel.setBackground(new Color(180, 241, 233));

        JPanel addPanel = new JPanel(new GridLayout(7, 4));

        lblIdLibrarie = new JLabel("IdLibrarie:");
        addPanel.add(lblIdLibrarie);
        addPanel.add(txtIdLibrarie);

        lblIdCarteLibrarie = new JLabel("IdCarteLibrarie:");
        addPanel.add(lblIdCarteLibrarie);
        addPanel.add(txtIdCarteLibrarie);

        lblIdCarte = new JLabel("IdCarte:");
        addPanel.add(lblIdCarte);
        addPanel.add(txtIdCarte);

        lblDomeniu = new JLabel("Domeniu:");
        addPanel.add(lblDomeniu);
        addPanel.add(txtDomeniu);

        lblTitlu = new JLabel("Titlu:");
        addPanel.add(lblTitlu);
        addPanel.add(txtTitlu);

        lblAutor = new JLabel("Autor:");
        addPanel.add(lblAutor);
        addPanel.add(txtAutor);

        lblPret = new JLabel("Pret:");
        addPanel.add(lblPret);
        addPanel.add(txtPret);

        lblDisponibil = new JLabel("Disponibil:");
        addPanel.add(lblDisponibil);
        addPanel.add(txtDisponibil);

        lblEditura = new JLabel("Editura:");
        addPanel.add(lblEditura);
        addPanel.add(txtEditura);
        addPanel.add(Box.createHorizontalGlue());

        addPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        addPanel.add(btnAdauga);
        addPanel.add(btnSterge);
        addPanel.add(btnActualizeaza);
        btnAdauga.setPreferredSize(new Dimension( 40, 25));
        btnSterge.setPreferredSize(new Dimension( 40, 25));
        btnActualizeaza.setPreferredSize(new Dimension( 40, 25));

        addPanel.setBackground(new Color(180, 238, 217));


        panelAngajat.add(addPanel, BorderLayout.NORTH);


        // add the table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        panelAngajat.add(tablePanel, BorderLayout.CENTER);
        panelAngajat.setSize(800, 700);

        // add the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnEnglish);
        buttonPanel.add(btnFrench);
        buttonPanel.add(btnSpanish);
        buttonPanel.add(btnRomanian);
        panelAngajat.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBackground(new Color(180, 238, 217));

    }
    public JComboBox<String> getCriteriu() {
        return comboCriteriu;
    }

    public JTextField gettxtFiltru(){return txtFiltru;}

    public JLabel getLblIdLibrarie() {return lblIdLibrarie;}

    public JLabel getLblIdCarteLibrarie() {
        return lblIdCarteLibrarie;
    }

    public JLabel getLblIdCarte() {
        return lblIdCarte;
    }

    public JLabel getLblDomeniu() {
        return lblDomeniu;
    }

    public JLabel getLblTitlu() {
        return lblTitlu;
    }

    public JLabel getLblAutor() {
        return lblAutor;
    }

    public JLabel getLblPret() {
        return lblPret;
    }

    public JLabel getLblDisponibil() {
        return lblDisponibil;
    }

    public JLabel getLblEditura() {
        return lblEditura;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }
    public JButton getBtnAdauga() {
        return btnAdauga;
    }
    public JButton getBtnSterge() {
        return btnSterge;
    }
    public JButton getBtnActualizeaza() {
        return btnActualizeaza;
    }
    public JButton getBtnCauta() {
        return btnCauta;
    }
    public JButton getBtnRomanian() {return btnRomanian;}
    public JButton getBtnFrench() {return btnFrench;}
    public JButton getBtnEnglish() {return btnEnglish;}
    public JButton getBtnSpanish() {return btnSpanish;}
    public JLabel getLblCriteriu() {return lblCriteriu;}
    public JLabel getLblFiltru() {return lblFiltru;}

    public JTextField gettxtIdLibrarie() {return txtIdLibrarie;}
    public JTextField gettxtIdCarteLibrarie() {
        return txtIdCarteLibrarie;
    }
    public JTextField gettxtIdCarte() {
        return txtIdCarte;
    }
    public JTextField gettxtDomeniu() {
        return txtDomeniu;
    }
    public JTextField gettxtTitlu() {
        return txtTitlu;
    }
    public JTextField gettxtAutor() {
        return txtAutor;
    }
    public JTextField gettxtPret() {
        return txtPret;
    }
    public JTextField gettxtDisponibil() {
        return txtDisponibil;
    }
    public JTextField gettxtEditura() {
        return txtEditura;
    }
    public JTable getTable(){return table;}

    public JLabel getAddMessage(){return  addMessage;}
    public JLabel getDeleteMessage(){return  deleteMessage;}
    public JLabel getUpdateMessage(){return  updateMessage;}
    public void setCriteriuLabels(String label, String[] options) {
        lblCriteriu.setText(label);
        comboCriteriu.setModel(new DefaultComboBoxModel<>(options));
    }
    public void setTable(DefaultTableModel model) {
        table.setModel(model);
        scrollPane.setViewportView(table);
    }
}
