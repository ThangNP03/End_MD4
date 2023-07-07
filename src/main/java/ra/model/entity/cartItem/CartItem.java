package ra.model.entity.cartItem;

public class CartItem {
    private  int id;
    private int orderId;
    private int productId;
    private  double price;
    private int quantity;
    private String name;
    private String urlImageP;
    private int user_id;
    private  String createDate;
    private double total;
    private boolean status;
    private String phone ;
    private String address;

    public CartItem() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }



    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public CartItem(int id, int orderId, int productId, double price, int quantity, String name, String urlImageP, int user_id, String createDate, double total, boolean status, String phone, String address) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.urlImageP = urlImageP;
        this.user_id = user_id;
        this.createDate = createDate;
        this.total = total;
        this.status = status;
        this.phone = phone;
        this.address = address;
    }

    public CartItem(int id, int orderId, int productId, double price, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;

    }

    public CartItem(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImageP() {
        return urlImageP;
    }

    public void setUrlImageP(String urlImageP) {
        this.urlImageP = urlImageP;
    }
}
