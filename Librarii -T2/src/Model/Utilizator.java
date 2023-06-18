package Model;


public class Utilizator extends Librarie{
    private final int idUtilizator;
    private final String username;
    private String parola;
    private String tip;

    public Utilizator(int idUtilizator, String username, String parola, String tip, int idLibrarie) {
        super(idLibrarie);
        this.idUtilizator = idUtilizator;
        this.username = username;
        this.parola = parola;
        this.tip = tip;

    }

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public String getUser() {
        return username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getIdLibrarie() {
        return idLibrarie;
    }

    public void setIdLibrarie(int idLibrarie) {
        this.idLibrarie = idLibrarie;
    }


    @Override
    public String toString() {
        return "Utilizator{" +
                "idUtilizator=" + idUtilizator +
                ", idLibrarie=" + idLibrarie +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }
}
