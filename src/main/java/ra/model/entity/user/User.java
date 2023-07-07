package ra.model.entity.user;

public class User {
    private int id;
    private String username;
    private String fullName;
    private String phone_number;
    private String address;
    private String password;
    private int role;
    private int status;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String fullName, String phone_number, String address, String password) {
        this.username = username;
        this.fullName = fullName;
        this.phone_number = phone_number;
        this.address = address;
        this.password = password;
    }

    public User(int id, String username, String fullName, String phone_number, String address, String password, int role, int status) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.phone_number = phone_number;
        this.address = address;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    //    public User(int id, String username, String fullName, String phone_number, String address, String password, int role) {
//        this.id = id;
//        this.username = username;
//        this.fullName = fullName;
//        this.phone_number = phone_number;
//        this.address = address;
//        this.password = password;
//        this.role = role;
//    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}

