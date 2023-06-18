package Model;

public class Carte {

        private int idCarte;
        private String domeniu, titlu, autor;

    public Carte() {
    }
    public Carte(int idCarte, String domeniu, String titlu, String autor) {
        this.idCarte = idCarte;
        this.domeniu = domeniu;
        this.titlu = titlu;
        this.autor = autor;
    }

    public int getIdCarte() {
        return idCarte;
    }

    public String getDomeniu() {
        return domeniu;
    }

    public void setDomeniu(String domeniu) {
        this.domeniu = domeniu;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    @Override
    public String toString() {
        return "Carte{" +
                "idCarte=" + idCarte +
                ", domeniu='" + domeniu + '\'' +
                ", titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }

}
