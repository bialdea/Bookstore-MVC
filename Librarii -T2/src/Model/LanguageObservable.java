package Model;

import java.util.Observable;
import java.util.Locale;
import java.util.Observer;
import java.util.ResourceBundle;

public class LanguageObservable extends Observable {
    private ResourceBundle resourceBundle;
    public LanguageObservable() {
        this.resourceBundle = ResourceBundle.getBundle("MessageBundle", new Locale("ro","RO"));
    }
    public void setLanguage(String language) {
        setChanged();
        notifyObservers(language);
    }
}
