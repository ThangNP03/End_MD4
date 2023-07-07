package ra.model.service;

import java.sql.SQLException;
import java.util.List;

public interface IService <T,E>{
    List<T> findAll() throws SQLException;
    boolean save(T t) throws SQLException;
    boolean update (T t) throws SQLException;
    T findById(E e) throws SQLException;
    boolean delete(E e) throws SQLException;
}
