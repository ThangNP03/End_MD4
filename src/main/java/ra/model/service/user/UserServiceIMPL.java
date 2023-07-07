package ra.model.service.user;

import ra.model.entity.user.User;
import ra.model.entity.user.UserLogin;
import ra.model.untill.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService{









//    public List<User> findAll() {
//        List<User> list_user = new ArrayList<>();
//        Connection conn = null;
//        try {
//            conn = ConnectionToDB.getConnection();
//            CallableStatement callst = conn.prepareCall("{call PROC_GETALL_USER()}");
//            ResultSet rs = callst.executeQuery();
//            while (rs.next()){
//                User u = new User();
//                u.setId(rs.getInt("id"));
//                u.setUsername(rs.getString("username"));
//                u.setFullName(rs.getString("fullName"));
//                u.setPhone_number(rs.getString("phone_number"));
//                u.setAddress(rs.getString("address"));
//                u.setPassword(rs.getString("password"));
//                u.setRole(rs.getInt("role_id"));
//                list_user.add(u);
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            ConnectionToDB.close(conn);
//        }
//        return list_user;
//
//    }


    @Override
    public List<User> findAll() throws SQLException {
        List<User> list_user = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_GETALL_USER()}");
            ResultSet rs = callst.executeQuery();
            while (rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullName(rs.getString("fullName"));
                u.setPhone_number(rs.getString("phone_number"));
                u.setAddress(rs.getString("address"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getInt("role_id"));
                u.setStatus(rs.getInt("status"));
                list_user.add(u);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.close(conn);
        }
        return list_user;
    }

    @Override
    public boolean save(User user) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_REGISTER(?,?,?,?,?)}");
            callst.setString(1,user.getUsername());
            callst.setString(2, user.getFullName());
            callst.setString(3,user.getPhone_number());
            callst.setString(4,user.getAddress());
            callst.setString(5,user.getPassword());


            callst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            ConnectionToDB.close(conn);
        }
        return true;
    }

    @Override
    public boolean update(User user) throws SQLException {
        return false;
    }



    @Override
    public User findById(Integer cartId) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }
    @Override
    public UserLogin login(User user) {
        UserLogin userLogin = null;
        Connection conn  = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_LOGIN(?,?)}");
            callst.setString(1, user.getUsername());
            callst.setString(2, user.getPassword());
            ResultSet rs = callst.executeQuery();
            if(rs.next()){
                userLogin = new UserLogin();
                userLogin.setId(rs.getInt("id"));
                userLogin.setUsername(rs.getString("username"));
                userLogin.setFullName(rs.getString("fullName"));
                userLogin.setPhone_number(rs.getString("phone_number"));
                userLogin.setAddress(rs.getString("address"));
                userLogin.setPassword(rs.getString("password"));
                userLogin.setCartId(rs.getInt("orderId"));
                userLogin.setRole(rs.getInt("role_id"));
                userLogin.setStatus(rs.getInt("status"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userLogin;
    }

    @Override
    public boolean updateStatusUser(Integer id, Integer status) {
            boolean st = (status==1)?true:false;
            Connection conn = null;
            try {
                conn = ConnectionToDB.getConnection();
                CallableStatement callst = conn.prepareCall("{call UpdateAccountStatus(?,?)}");
                callst.setInt(2, id);
                callst.setBoolean(1,st);
                callst.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                ConnectionToDB.close(conn);
            }
            return true;
        }




    @Override
    public boolean checkExistsUsername(String username) {

        Connection conn = null;
        try {
            conn= ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FINDBYUSERNAME(?)}");
            callSt.setString(1,username);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean checkExitsPhoneNumber(String phone_number) {
        Connection conn = null;
        try {
            conn= ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FINDBYPHONENUMBER(?)}");
            callSt.setString(1,phone_number);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public UserLogin findAllUser(UserLogin userLogin) {
        return null;
    }

}

