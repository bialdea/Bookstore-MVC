package Controller;

import Model.UtilizatorPersistenta;
import Model.Utilizator;
import Model.LanguageObservable;
import java.util.Locale;
import java.util.ResourceBundle;

import View.LoginGui;

import javax.swing.*;

public class LoginController {
    private final LoginGui loginView;
    private final UtilizatorPersistenta utilizatorPersistenta;
    private final LanguageObservable languageObservable;

    public LoginController() {
        this.loginView = new LoginGui();
        this.utilizatorPersistenta = new UtilizatorPersistenta();
        this.languageObservable = new LanguageObservable();

        JButton btnRomanian = this.loginView.getBtnRomanian();
        btnRomanian.addActionListener(e -> {
            Locale.setDefault(new Locale("ro","RO"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("ro_RO");
        });


        JButton btnEnglish = this.loginView.getBtnEnglish();
        btnEnglish.addActionListener(e -> {
            Locale.setDefault(new Locale("en","US"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("en_US");
        });

        JButton btnFrench = this.loginView.getBtnFrench();
        btnFrench.addActionListener(e -> {
            Locale.setDefault(new Locale("fr","FR"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("fr_FR");
        });

        JButton btnSpanish = this.loginView.getBtnSpanish();
        btnSpanish.addActionListener(e -> {
            Locale.setDefault(new Locale("es","ES"));
            ResourceBundle.getBundle("MessageBundle");
            languageObservable.setLanguage("es_ES");
        });

        languageObservable.addObserver((observable, arg) -> {
            String language = (String) arg;
            ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle", new Locale(language.substring(0, 2), language.substring(4)));
            String usCancel = bundle.getString("cancelButton");
            String usLogin = bundle.getString("loginButton");
            String usLoginForm = bundle.getString("autentificareutilizator");
            String usnumeUtilizator = bundle.getString("numeUtilizator");
            String parola = bundle.getString("parola");
            String errorMessage = bundle.getString("error");


            loginView.getBtnCancel().setText(usCancel);
            loginView.getLoginButton().setText(usLogin);
            loginView.getlbUser().setText(usnumeUtilizator);
            loginView.getlbPassword().setText(parola);
            loginView.getlbLoginForm().setText(usLoginForm);
            loginView.geterrorMessage().setText(errorMessage);

        });


        JButton btnCancel = loginView.getBtnCancel();
        btnCancel.addActionListener(e -> {
            loginView.setVisible(false);
            loginView.dispose();
                });

        JButton loginButton = loginView.getLoginButton();
        loginButton.addActionListener(e -> {
            String numeUtilizator = loginView.getTfUser().getText();
            String parola = loginView.getPfPassword().getText();


            Utilizator utilizator = utilizatorPersistenta.cautareUtilizator(numeUtilizator, parola);

            if (utilizator == null) {
                JOptionPane.showMessageDialog(new JFrame("JOptionPane"),loginView.geterrorMessage(),"",JOptionPane.ERROR_MESSAGE);
            } else if (!utilizator.getParola().equals(parola)) {
                JOptionPane.showMessageDialog(new JFrame("JOptionPane"),loginView.geterrorMessage(),"",JOptionPane.ERROR_MESSAGE);
            } else if (utilizator.getTip().equals("administrator")) {
                AdministratorController administratorController = new AdministratorController();
                administratorController.showAdministratorGUI();
            } else if (utilizator.getTip().equals("manager")) {
                ManagerController managerController = new ManagerController();
                managerController.showManagerGUI();
            } else if (utilizator.getTip().equals("angajat")) {
                utilizator.getIdLibrarie();
                AngajatController angajatController = new AngajatController();
                angajatController.showAngajatGUI();
            } else {
                JOptionPane.showMessageDialog(new JFrame("JOptionPane"),loginView.geterrorMessage(),"",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void showLoginGUI() {
        loginView.setVisible(true);
    }
}
