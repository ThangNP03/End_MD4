package ra.model.service.product;

import ra.model.entity.products.Product;
import ra.model.service.catalog.CatalogServiceIMPL;
import ra.model.untill.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceIMPL implements IProductService{
    @Override
    public List<Product> findAll() throws SQLException {
        CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_GETALL_PRODUCT()}");
            ResultSet rs = callst.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setIdP(rs.getInt("idP"));
                product.setNameC(rs.getString("nameP"));
                product.setCatalog(catalogServiceIMPL.findById(rs.getInt("idC")));
                product.setUrlImageP(rs.getString("urlImageP"));
                product.setQuantity(rs.getInt("quantity"));
                product.setTitle(rs.getString("title"));
                product.setPriceP(rs.getFloat("priceP"));
                product.setStatus(rs.getBoolean("status"));
                products.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.close(conn);
        }
        return products;
    }


    @Override
    public boolean save(Product product) throws SQLException {
        final String SQL_CREATE_PRD = "INSERT INTO product (nameP,idC, urlImageP, quantity,title, priceP, status) values (?,?,?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall(SQL_CREATE_PRD);
            callst.setString(1, product.getNameC());
            callst.setInt(2,product.getCatalog().getIdC());
            callst.setInt(4,product.getQuantity());
            callst.setString(5, product.getTitle());
            callst.setFloat(6,product.getPriceP());
            callst.setBoolean(7, product.isStatus());
            callst.setString(3,product.getUrlImageP());
            callst.executeUpdate();
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            ConnectionToDB.close(conn);
        }
    }

    @Override
    public boolean update(Product product) throws SQLException {
        final String SQL_UPDATE_PRD = "UPDATE product   set idC = ?, nameP = ?, urlImageP= ? ,quantity = ? ,title = ?,priceP = ? , status = ? where  idP = ?;";
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall(SQL_UPDATE_PRD);
            callst.setInt(1,product.getCatalog().getIdC());
            callst.setString(2, product.getNameC());
            callst.setString(3,product.getUrlImageP());
            callst.setInt(4,product.getQuantity());
            callst.setString(5,product.getTitle());
            callst.setFloat(6,product.getPriceP());
            callst.setBoolean(7,product.isStatus());
            callst.setInt(8, product.getIdP());
            callst.executeUpdate();
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            ConnectionToDB.close(conn);
        }
    }


    @Override
    public List<Product> findProductByPage(int page, int count) {
        List<Product> list = new ArrayList<>();
        CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_FindProductByPage(?,?)}");
            callst.setInt(1, page);
            callst.setInt(2,count);
            ResultSet rs = callst.executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setIdP(rs.getInt("idP"));
                p.setNameC(rs.getString("nameP"));
                p.setUrlImageP(rs.getString("urlImageP"));
                p.setCatalog(catalogServiceIMPL.findById(rs.getInt("idC")));
                p.setQuantity(rs.getInt("quantity"));
                p.setTitle(rs.getString("title"));
                p.setPriceP(rs.getFloat("priceP"));
                p.setStatus(rs.getBoolean("status"));
                list.add(p);
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
    public Product findById(Integer id) throws SQLException {
        CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
        Product product = new Product();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_FINDBYID_PRODUCT(?)}");
            callst.setInt(1, id);
            ResultSet rs = callst.executeQuery();
            while (rs.next()){
                product.setIdP(rs.getInt("idP"));
                product.setNameC(rs.getString("nameP"));
                product.setCatalog(catalogServiceIMPL.findById(rs.getInt("idC")));
                product.setUrlImageP(rs.getString("urlImageP"));
                product.setQuantity(rs.getInt("quantity"));
                product.setTitle(rs.getString("title"));
                product.setPriceP(rs.getFloat("priceP"));
                product.setStatus(rs.getBoolean("status"));
               return product;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            ConnectionToDB.close(conn);
        }
        return product;
    }



    @Override
    public boolean delete(Integer  id) throws SQLException {
        Connection conn = null ;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callst = conn.prepareCall("{call PROC_DELETE_PRODUCT(?)}");
            callst.setInt(1, id);
            callst.executeQuery();
            return true;
        }catch ( Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
