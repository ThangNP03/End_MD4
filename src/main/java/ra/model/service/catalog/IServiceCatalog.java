package ra.model.service.catalog;

import ra.model.entity.catalog.Catalog;
import ra.model.service.IService;

import java.sql.SQLException;
import java.util.List;

public interface IServiceCatalog extends IService<Catalog, Integer> {
    List<Catalog> findByName(String nameC) throws SQLException;
}
