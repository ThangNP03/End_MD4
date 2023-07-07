package ra.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.cartItem.CartItem;
import ra.model.entity.order.OrderDetail;
import ra.model.entity.user.UserLogin;
import ra.model.service.odder.CartServiceIMPL;
import ra.model.service.orderDetail.OrderServiceIMPL;

import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/orderController")
public class OrderController {
    OrderServiceIMPL orderServiceIMPL = new OrderServiceIMPL();
    @PostMapping("/create")
    public String create(@RequestParam("phone_number") String phone_number, @RequestParam("address") String address, HttpSession session){
        UserLogin userLogin = (UserLogin) session.getAttribute("userLogin");
        CartServiceIMPL cartServiceIMPL = new CartServiceIMPL();
        List<CartItem> listCart = cartServiceIMPL.FindAllByCartId(userLogin.getCartId());
        double total = 0.0;
        for (CartItem o : listCart) {
            total += o.getPrice()* o.getQuantity();
        }
        int newCartId = orderServiceIMPL.createNewOrder(new OrderDetail(userLogin.getCartId(),phone_number,address,total));
        userLogin.setCartId(newCartId);
        session.setAttribute("userLogin",userLogin);
        return "cart/addToCart";
    }
}
