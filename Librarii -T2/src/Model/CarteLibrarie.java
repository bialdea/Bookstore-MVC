package Model;

public class CarteLibrarie extends Carte {
    private float pret;
    private int disponibil, idCarteLibrarie, idLibrarie;
    private String editura;

    public CarteLibrarie(){}
    public CarteLibrarie(float pret, int disponibil, int idCarteLibrarie, String editura, int idLibrarie, int idCarte, String domeniu, String titlu, String autor) {
        super(idCarte, domeniu, titlu, autor);
        this.pret = pret;
        this.disponibil = disponibil;
        this.idCarteLibrarie = idCarteLibrarie;
        this.editura = editura;
        this.idLibrarie = idLibrarie;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getDisponibil() {
        return disponibil;
    }

    public void setDisponibil(int disponibil) {
        this.disponibil = disponibil;
    }

    public int getIdCarteLibrarie() {
        return idCarteLibrarie;
    }

    public void setIdCarteLibrarie(int idCarteLibrarie) {
        this.idCarteLibrarie = idCarteLibrarie;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public int getIdLibrarie() {
        return idLibrarie;
    }

    public void setIdLibrarie(int idLibrarie) {
        this.idLibrarie = idLibrarie;
    }

    @Override
    public String toString() {
        return "CarteLibrarie{" +
                "pret=" + pret +
                ", disponibil=" + disponibil +
                ", idCarteLibrarie=" + idCarteLibrarie +
                ", idLibrarie=" + idLibrarie +
                ", editura='" + editura + '\'' +
                '}';
    }
}

