package ra.model.entity.order;

public class OrderDetail {
    private int id;
    private int orderId;
    private int productId;
    private String productName;
    private String urlImageP;
    private String description;
    private  float price;
    private int quantity;
    private  String phone;
    private String address;
    private int status;
    private String createDate;
    private double total;

    public double getTotal() {
        return total;
    }



    public OrderDetail( int orderId, String phone, String address, double total ) {

        this.orderId = orderId;
        this.phone = phone;
        this.address = address;
        this.total = total;
    }

    public OrderDetail( int orderId, int productId, float price, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }
    
    public OrderDetail(int id, int orderId, int productId, String productName, String urlImageP, String description, float price, int quantity, String phone, String address, int status, String  createDate) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.urlImageP = urlImageP;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.createDate = createDate;
    }



    public void setTotal(double total) {
        this.total = total;
    }

    public OrderDetail() {
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getUrlImageP() {
        return urlImageP;
    }

    public void setUrlImageP(String urlImageP) {
        this.urlImageP = urlImageP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
