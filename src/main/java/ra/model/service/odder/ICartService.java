package ra.model.service.odder;

import ra.model.entity.cartItem.CartItem;
import ra.model.entity.order.OrderDetail;
import ra.model.service.IService;

import java.sql.SQLException;
import java.util.List;

public interface ICartService extends IService<CartItem, Integer> {
    boolean clearCart(int cartId);
    List<CartItem>  FindAllByCartId(int cartId);
    CartItem findCartItemByID(int cartId, int productId);


}
