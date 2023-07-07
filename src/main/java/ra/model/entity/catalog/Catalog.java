package ra.model.entity.catalog;

public class Catalog {
    private int idC;
    private String nameC;

    public Catalog() {
    }

    public Catalog(int idC, String nameC) {
        this.idC = idC;
        this.nameC = nameC;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int catalog) {
        this.idC = catalog;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }
}
