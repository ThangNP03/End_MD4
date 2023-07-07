package ra.model.service.odder;

import ra.model.entity.cartItem.CartItem;
import ra.model.entity.order.OrderDetail;
import ra.model.untill.ConnectionToDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartServiceIMPL implements ICartService{
    @Override
    public List<CartItem> findAll() throws SQLException {
        Connection conn = null;
        List<CartItem> list = new ArrayList<>();
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_FindAllOrders()}");
            ResultSet rs = callst.executeQuery();
            while (rs.next()){
                CartItem c = new CartItem();
                c.setOrderId(rs.getInt("orderId"));
                c.setUser_id(rs.getInt("user_id"));
                c.setPrice(rs.getDouble("total"));
                c.setCreateDate(rs.getString("createdDate"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                list.add(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.close(conn);
        }

        return list;
    }


    @Override
    public CartItem findById(Integer cartItemId) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_DeleteOrderDetail(?)}");
            callSt.setInt(1, integer);
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean save(CartItem cartItem) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_CreateOrderDetail(?,?,?,?)}");
            callSt.setInt(1, cartItem.getOrderId());
            callSt.setInt(2, cartItem.getProductId());
            callSt.setDouble(3, cartItem.getPrice());
            callSt.setInt(4, cartItem.getQuantity());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean update(CartItem cartItem) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_ChangeQuantity(?,?)}");
            callSt.setInt(1, cartItem.getId());
            callSt.setInt(2, cartItem.getQuantity());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean clearCart(int cartId) {
        Connection conn = null;

        try {
            conn= ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_ClearCartDetail(?)}");
            callSt.setInt(1,cartId);
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                ConnectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    @Override
    public List<CartItem> FindAllByCartId(int cartId) {
        List<CartItem> list = new ArrayList<>();
        Connection conn = null;

        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FindListOrderDetail(?)}");
            callSt.setInt(1, cartId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                CartItem c = new CartItem();
                c.setId(rs.getInt("id"));
                c.setOrderId(rs.getInt("order_id"));
                c.setProductId(rs.getInt("product_id"));
                c.setPrice(rs.getDouble("product_price"));
                c.setQuantity(rs.getInt("quantity"));
                c.setName(rs.getString("nameP"));
                c.setUrlImageP(rs.getString("urlImageP"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public CartItem findCartItemByID(int cartId, int productId) {
        List<CartItem> list = FindAllByCartId(cartId);
        for (CartItem c : list) {
            if (c.getProductId() == productId) {
                return c;
            }
        }
        return null;
    }
}

//    @Override
//    public List<OrderDetail> findCartByOrderId(Integer orderId) throws SQLException {
//        List<OrderDetail> list = new ArrayList<>();
//        Connection conn = ConnectionToDB.getConnection();
//        CallableStatement callst = conn.prepareCall("{call PROC_FindListOrderDetail(?)}");
//        callst.setInt(1,orderId);
//        ResultSet rs = callst.executeQuery();
//        while (rs.next()){
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setId(rs.getInt("id"));
//            orderDetail.setOrderId(rs.getInt("order_id"));
//            orderDetail.setProductId(rs.getInt("product_id"));
//            orderDetail.setPrice(rs.getFloat("product_price"));
//            orderDetail.setQuantity(rs.getInt("quantity"));
//            orderDetail.setProductName(rs.getString("nameP"));
//            orderDetail.setUrlImageP(rs.getString("urlImageP"));
//
//            list.add(orderDetail);
//        }
//        return list;
//    }

//    @Override
//    public OrderDetail checkExistProduct(Integer proId, Integer cartId) throws SQLException {
//        List<OrderDetail> list = findCartByOrderId(cartId);
//        for (OrderDetail o : list) {
//            if (o.getProductId() == proId) {
//                return o;
//            }
//        }
//        return null;
//    }}


