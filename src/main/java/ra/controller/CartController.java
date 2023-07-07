package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.cartItem.CartItem;
import ra.model.entity.order.OrderDetail;
import ra.model.entity.products.Product;
import ra.model.entity.user.UserLogin;
import ra.model.service.odder.CartServiceIMPL;
import ra.model.service.product.ProductServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cartController")
public class CartController {
    CartServiceIMPL cartServiceIMPL = new CartServiceIMPL();
    ProductServiceIMPL productServiceIMPL = new ProductServiceIMPL();
    @GetMapping("/")
    public String product_cart(Model model) throws SQLException {
        List<Product> listProduct = productServiceIMPL.findAll();
        List<Product> listPage  = productServiceIMPL.findProductByPage(1,12);
        int pageCount  =  (listProduct.size()%12)==0? (listProduct.size() / 12):(listProduct.size()/12 +1);
        model.addAttribute("listProduct", listPage);
        model.addAttribute("pageCount", new Array[pageCount]);
        model.addAttribute("pageCurrent",1);
        return "cart/product_cart";
    }
    @GetMapping("/backProduct")
    public String back_product(){
        return "redirect:/productController/productCart";
    }

    @GetMapping("/page/{id}")
    public  String page (@PathVariable("id") String id , Model model) throws SQLException {
        List<Product>listProduct = productServiceIMPL.findAll();

        model.addAttribute("listProduct",listProduct);

        return "product/product_list";
    }
    @GetMapping("/cart")
    public  String show_cart_view(HttpServletRequest request , Model model) throws SQLException {

        UserLogin userLogin = (UserLogin) request.getSession().getAttribute("userLogin");
       if (userLogin == null){
           return "redirect:/login";
       }
       else {
           List<CartItem> listCart = cartServiceIMPL.FindAllByCartId(userLogin.getCartId());
           model.addAttribute("listCart", listCart);
           double total = 0.0;
           for (CartItem o : listCart) {
               total += o.getPrice()* o.getQuantity();
           }
           model.addAttribute("total", total);
           return "cart/addToCart";
       }


    }

    @GetMapping("/add-to-cart/{proId}")
    public String addCart(@PathVariable("proId") String  proId , HttpSession session) throws SQLException {
        UserLogin userLogin  = (UserLogin) session.getAttribute("userLogin");
        Product p = productServiceIMPL.findById(Integer.valueOf(proId));
        CartItem cartItem =  cartServiceIMPL.findCartItemByID(userLogin.getCartId(),Integer.parseInt(proId));
        if (cartItem == null) {
            cartServiceIMPL.save(new CartItem(0,userLogin.getCartId(),p.getIdP(),p.getPriceP(),1));
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartServiceIMPL.update(cartItem);
        }
        return "redirect:/cartController/cart";

    }
    @PostMapping("/update")
    public  String update (@RequestParam ("cdId") String cdId , @RequestParam("quantity") String quantity ) throws SQLException {
        cartServiceIMPL.update(new CartItem(Integer.valueOf(cdId),Integer.valueOf(quantity)));
        return "redirect:/cartController/cart";
    }
    @GetMapping("/delete/{idDel}")
    public String delete(@PathVariable("idDel") String idDel) throws SQLException {
        cartServiceIMPL.delete(Integer.valueOf(idDel));
        return "redirect:/cartController/cart";
    }
    @GetMapping("/order")
    public  String order(Model model,HttpSession session) throws SQLException {

       List<CartItem>listOrder = cartServiceIMPL.findAll();
        model.addAttribute("listOrder", listOrder);
        System.out.println(listOrder);
        return "/order/orderManager";
    }

}
