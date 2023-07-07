package ra.model.service.orderDetail;

import ra.model.entity.order.OrderDetail;
import ra.model.untill.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class OrderServiceIMPL implements IOderService{
    @Override
    public int createNewOrder(OrderDetail o) {
        int newOrderId = -1;
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_CREATEORDERNEW(?,?,?,?,?)}");
            callst.setInt(1, o.getOrderId());
            callst.setString(2, o.getPhone());
            callst.setString(3, o.getAddress());
            callst.setDouble(4, o.getTotal());
            callst.setInt(5, 1);
//            callst.setDouble(5,);
            callst.executeUpdate();
            newOrderId = callst.getInt(5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.close(conn);

        }
        return newOrderId;
    }
}
