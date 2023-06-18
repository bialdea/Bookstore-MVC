package View;

import javax.swing.*;
import java.awt.*;


public class LoginGui extends JFrame {

    private final JTextField tfUser;
    private final JLabel lbLoginForm;
    private final JLabel lbUser;
    private final JLabel lbPassword;
    private final JPasswordField pfPassword;
    private final JButton btnLogin;
    private final JButton btnCancel;
    private final JButton btnRomanian;
    private final JButton btnEnglish;
    private final JButton btnSpanish;
    private final JButton btnFrench;
    private final JLabel errorMessage;

    public LoginGui() {
        super("Login");

        lbLoginForm = new JLabel("Autentificare utilizator", SwingConstants.CENTER);
        Font mainFont = new Font("Segoe Print", Font.BOLD, 20);
        lbLoginForm.setFont(mainFont);

        lbUser = new JLabel("Nume Utilizator");
        Font font1 = new Font("Segoe Print", Font.BOLD, 18);
        lbUser.setFont(font1);

        tfUser = new JTextField();
        tfUser.setFont(font1);

        lbPassword = new JLabel("Parola");
        lbPassword.setFont(font1);

        pfPassword = new JPasswordField();
        pfPassword.setFont(font1);

        ImageIcon Romanian = new ImageIcon("src/resurse/Romania.png");
        btnRomanian = new JButton();
        btnRomanian.setIcon(Romanian);
        btnRomanian.setBorderPainted(false);

        ImageIcon image = new ImageIcon("src/resurse/poze.png");
        btnEnglish = new JButton();
        btnEnglish.setIcon(image);
        btnEnglish.setBorderPainted(false);


        ImageIcon French = new ImageIcon("src/resurse/France.png");
        btnFrench = new JButton();
        btnFrench.setIcon(French);
        btnFrench.setBorderPainted(false);


        ImageIcon Spanish = new ImageIcon("src/resurse/Spain.png");
        btnSpanish = new JButton();
        btnSpanish.setIcon(Spanish);
        btnSpanish.setBorderPainted(false);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 20, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(70, 70, 30, 70));
        formPanel.add(lbLoginForm);
        formPanel.add(lbUser);
        formPanel.add(tfUser);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);

        btnLogin = new JButton("Autentificare");
        btnCancel = new JButton("Anulare");
        errorMessage= new JLabel("Utilizator sau parola invalida");


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 4, 10, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);
        buttonsPanel.add(new JLabel());
        buttonsPanel.add(new JLabel());
        buttonsPanel.add(btnEnglish);
        buttonsPanel.add(btnFrench);
        buttonsPanel.add(btnSpanish);
        buttonsPanel.add(btnRomanian);

        add(formPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);

        setTitle("");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JTextField getTfUser() {
        return tfUser;
    }

    public JPasswordField getPfPassword() {
        return pfPassword;
    }

    public JButton getLoginButton() {
        return btnLogin;
    }
    public JButton getBtnCancel() {
        return btnCancel;
    }
    public JButton getBtnRomanian() {return btnRomanian;}
    public JButton getBtnFrench() {return btnFrench;}
    public JButton getBtnEnglish() {return btnEnglish;}
    public JButton getBtnSpanish() {return btnSpanish;}

    public JLabel getlbLoginForm() {return lbLoginForm;}
    public JLabel getlbUser() {return lbUser;}

    public JLabel getlbPassword() {return lbPassword;}
    public JLabel geterrorMessage() {return errorMessage;}

}



