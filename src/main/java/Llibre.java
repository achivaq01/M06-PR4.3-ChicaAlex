public class Llibre {
    private int id;
    private String titol;
    private String autor;
    private int any;

    public Llibre(int id, String nom, String autor, int any){
        super();

        this.id = id;
        this.titol = nom;
        this.autor = autor;
        this.any = any;
    }

    public String getTitol() {
        return titol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

}
