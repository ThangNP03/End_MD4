package ra.model.service.catalog;


import ra.model.entity.catalog.Catalog;
import ra.model.untill.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogServiceIMPL implements IServiceCatalog {

    @Override
    public List<Catalog> findAll()  {
        List<Catalog> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_GETAL_Catalog()}");
            ResultSet rs = callst.executeQuery();
            while (rs.next()) {
                Catalog catalog = new Catalog();
                catalog.setIdC(rs.getInt("idC"));
                catalog.setNameC(rs.getString("nameC"));
                list.add(catalog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.close(conn);
        }
        return list;
    }

    @Override
    public boolean save(Catalog catalog) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_CREATE_CATALOG(?)}");
            callst.setString(1, catalog.getNameC());
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
    public boolean update(Catalog catalog) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_UPDATECATALOG(?,?)}");
            callst.setInt(1, catalog.getIdC());
            callst.setString(2, catalog.getNameC());
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
    public Catalog findById(Integer integer) throws SQLException {
        Connection conn = null;
        Catalog catalog = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_FINDBYID_CATALOG(?)}");
            callst.setInt(1, integer);
            ResultSet rs = callst.executeQuery();
            if (rs.next()) {
                catalog = new Catalog();
                catalog.setIdC(rs.getInt(1));
                catalog.setNameC(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.close(conn);
        }
        return catalog;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_DELETECATALOG(?)}");
            callst.setInt(1, integer);
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
    public List<Catalog> findByName(String nameC) throws SQLException {
        List<Catalog> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_FINDBYNAME_CATALOG(?)}");
            callst.setString(1, nameC);
            ResultSet rs = callst.executeQuery();
            if (rs.next()) {
                Catalog c = new Catalog();
                c.setIdC(rs.getInt("idC"));
                c.setNameC(rs.getString("nameC"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.close(conn);
        }
        return list;
    }
}
