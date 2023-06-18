package Model;

public class Librarie {
    public int idLibrarie;
    public String locatie;

    public int getIdLibrarie() {
        return idLibrarie;
    }

    public void setIdLibrarie(int idLibrarie) {
        this.idLibrarie = idLibrarie;
    }

    @Override
    public String toString() {
        return "Librarie{" +
                "idLibrarie=" + idLibrarie +
                ", locatie='" + locatie + '\'' +
                '}';
    }

    public Librarie(int idLibrarie, String locatie) {
        this.idLibrarie = idLibrarie;
        this.locatie = locatie;
    }
    public Librarie(int idLibrarie) {
        this.idLibrarie = idLibrarie;
    }
}
