package ra.model.entity.products;

import ra.model.entity.catalog.Catalog;

public class Product {
    private int  idP ;
    private String nameC;
    private Catalog catalog;
    private String  urlImageP;
    private int quantity;
    private String title;
    private float priceP;
    private boolean status;

    public Product() {
    }

    public Product(int idP, String nameC, Catalog catalog, String urlImageP, int quantity, String title, float priceP, boolean status) {
        this.idP = idP;
        this.nameC = nameC;
        this.catalog = catalog;
        this.urlImageP = urlImageP;
        this.quantity = quantity;
        this.title = title;
        this.priceP = priceP;
        this.status = status;
    }

    public Product(String nameC, Catalog catalog, String urlImageP, int quantity, String title, float priceP, boolean status) {
        this.nameC = nameC;
        this.catalog = catalog;
        this.urlImageP = urlImageP;
        this.quantity = quantity;
        this.title = title;
        this.priceP = priceP;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public Catalog getIdC() {
        return catalog;
    }



    public String getUrlImageP() {
        return urlImageP;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public void setUrlImageP(String urlImageP) {
        this.urlImageP = urlImageP;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPriceP() {
        return priceP;
    }

    public void setPriceP(float priceP) {
        this.priceP = priceP;
    }
}
