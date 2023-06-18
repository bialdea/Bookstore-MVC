package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdministratorGui extends JFrame {
    JScrollPane scrollPane = new JScrollPane();
    JTextField txtUsername = new JTextField();
    JTextField txtParola = new JTextField();
    JTextField txtTip = new JTextField();
    JTextField txtIdUtilizator = new JTextField();
    JTable table = new JTable();
    JTextField txtIdLibrarie = new JTextField();

    JLabel lblUsername;
    JLabel lblParola;
    JLabel lblTip;
    JLabel lblIdUtilizator;
    JLabel lblIdLibrarie;
    JButton btnEnglish;
    JButton btnRomanian;
    JButton btnSpanish;
    JButton btnFrench;
    JButton btnSterge;
    JButton btnActualizeaza;
    JButton btnRefresh;
    JButton btnAdauga;
    JLabel addMessage;
    JLabel deleteMessage;
    JLabel updateMessage;


    public AdministratorGui() {
        JPanel panelAdministrator = new JPanel();
        setContentPane(panelAdministrator);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnRefresh = new JButton("Reimprospateaza");
        btnAdauga = new JButton("Adauga");
        btnSterge = new JButton("Sterge");
        btnActualizeaza = new JButton("Actualizeaza");


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


        panelAdministrator.setLayout(new BorderLayout());
        addMessage= new JLabel("Adaugare efectuata cu succes!");
        deleteMessage= new JLabel("Stergere efectuata cu succes!");
        updateMessage= new JLabel("Actualizare efectuata cu succes!");
        JPanel filterPanel = new JPanel(new GridLayout(5, 2)); // setam un grid layout cu 5 randuri si 2 coloane
        // 5 etichete si 5 campuri text
        lblUsername = new JLabel("Nume Utilizator:");
        filterPanel.add(lblUsername);
        filterPanel.add(txtUsername);
        lblParola = new JLabel("Parola:");
        filterPanel.add(lblParola);
        filterPanel.add(txtParola);
        lblTip = new JLabel("Tip:");
        filterPanel.add(lblTip);
        filterPanel.add(txtTip);
        lblIdUtilizator = new JLabel("Id Utilizator:");
        filterPanel.add(lblIdUtilizator);
        filterPanel.add(txtIdUtilizator);
        lblIdLibrarie = new JLabel("Id Librarie: (DOAR PENTRU ANGAJATI)");
        filterPanel.add(lblIdLibrarie);
        filterPanel.add(txtIdLibrarie);
        panelAdministrator.add(filterPanel, BorderLayout.NORTH);

        filterPanel.setBackground(new Color(160, 196, 147));

        // add the table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        panelAdministrator.add(tablePanel, BorderLayout.CENTER);

        // add the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdauga);
        buttonPanel.add(btnSterge);
        buttonPanel.add(btnActualizeaza);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnEnglish);
        buttonPanel.add(btnFrench);
        buttonPanel.add(btnSpanish);
        buttonPanel.add(btnRomanian);

        buttonPanel.setBackground(new Color(160, 196, 147));
        panelAdministrator.add(buttonPanel, BorderLayout.SOUTH);
    }

    public JLabel getLblUsername() {return lblUsername;}
    public JLabel getLblIdUtilizator() {return lblIdUtilizator;}
    public JLabel getLblParola() {return lblParola;}
    public JLabel getLblTip() {return lblTip;}
    public JLabel getLblIdLibrarie() {return lblIdLibrarie;}
    public JTextField gettxtUsername() {
        return txtUsername;
    }
    public JTextField gettxtIdUtilizator() {
        return txtIdUtilizator;
    }
    public JTextField gettxtPassword() {
        return txtParola;
    }
    public JTextField gettxtRole() { //echivalentul la getRoleT
        return txtTip;
}
    public JTextField gettxtidLibrarie() {
        return txtIdLibrarie;
        }

    public JButton getBtnRomanian() {return btnRomanian;}
    public JButton getBtnFrench() {return btnFrench;}
    public JButton getBtnEnglish() {return btnEnglish;}
    public JButton getBtnSpanish() {return btnSpanish;}

    public JButton getBtnAdauga() {
        return btnAdauga;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public JButton getBtnSterge() {
        return btnSterge;
    }
    public JButton getBtnActualizeaza() {
        return btnActualizeaza;
    }
    public JLabel getAddMessage(){return  addMessage;}
    public JLabel getDeleteMessage(){return  deleteMessage;}
    public JLabel getUpdateMessage(){return  updateMessage;}
    public JTable getTable(){return table;}

    public void setTable(DefaultTableModel model) {
        table.setModel(model);
        scrollPane.setViewportView(table);
    }

}


