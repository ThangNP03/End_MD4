package ra.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.catalog.Catalog;
import ra.model.entity.products.Product;
import ra.model.service.catalog.CatalogServiceIMPL;
import ra.model.service.product.ProductServiceIMPL;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/productController")
public class ProductController {
    ProductServiceIMPL productServiceIMPL = new ProductServiceIMPL();
    CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
    @GetMapping("/productCart")
    public String showProduct(Model model) throws SQLException {
        List<Product>  productCart = productServiceIMPL.findAll();
        List<Product> listPage  = productServiceIMPL.findProductByPage(1,12);
        int pageCount  =  (productCart.size()%12)==0? (productCart.size() / 12):(productCart.size()/12 +1);
        model.addAttribute("pageCount", new Array[pageCount]);
        model.addAttribute("pageCurrent",1);
        model.addAttribute("productCart", listPage );
        return "cart/product_cart";

    }
    @GetMapping("/pageCartProduct/{id}")
    public  String pageCartProduct (@PathVariable("id") String id , Model model) throws SQLException {
        List<Product>listProduct = productServiceIMPL.findAll();
        List<Product> listPage = productServiceIMPL.findProductByPage(Integer.parseInt(id),12);
        int pageCount  =  (listProduct.size()%12)==0? (listProduct.size() / 12):(listProduct.size()/12 +1);
        model.addAttribute("productCart",listPage);
        model.addAttribute("pageCount",new Array[pageCount]);
        model.addAttribute("pageCurrent",Integer.parseInt(id));
        return "cart/product_cart";
    }

    @GetMapping("/getAll")
    public String getAll(Model model) throws SQLException {
        List<Product> listProduct = productServiceIMPL.findAll();
        List<Product> listPage  = productServiceIMPL.findProductByPage(1,12);
        int pageCount  =  (listProduct.size()%12)==0? (listProduct.size() / 12):(listProduct.size()/12 +1);
        model.addAttribute("listProduct", listPage);
        model.addAttribute("pageCount", new Array[pageCount]);
        model.addAttribute("pageCurrent",1);
        return "product/product_list";
    }
    @GetMapping("/page/{id}")
    public  String page (@PathVariable("id") String id , Model model) throws SQLException {
        List<Product>listProduct = productServiceIMPL.findAll();
        List<Product> listPage = productServiceIMPL.findProductByPage(Integer.parseInt(id),12);
        int pageCount  =  (listProduct.size()%12)==0? (listProduct.size() / 12):(listProduct.size()/12 +1);
        model.addAttribute("listProduct",listPage);
        model.addAttribute("pageCount",new Array[pageCount]);
        model.addAttribute("pageCurrent",Integer.parseInt(id));
        return "product/product_list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) throws SQLException {
        productServiceIMPL.delete(id);
        return "redirect:getAll";
    }

    @GetMapping("/add")
        public String add(Model model){
        List<Catalog> catalogList = catalogServiceIMPL.findAll();
       model.addAttribute("listCatalog", catalogList);
        return "product/createProduct";}

    @PostMapping("/create")

    public String add(
                        @RequestParam("nameC") String name,
                      @RequestParam("urlImageP") MultipartFile image,
                      @RequestParam("idC") int catalog,
                      @RequestParam("quantity") int quantity,
                      @RequestParam("title") String title,
                      @RequestParam("priceP") float priceP,
                      @RequestParam("status") boolean status,

                      Model model) throws IOException, SQLException {
        String uploadPath = "D:\\MD4\\Bai_Tap_MD4\\end_md4neww\\src\\main\\resources\\assets\\image";

        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = image.getOriginalFilename();
        FileCopyUtils.copy(image.getBytes(), new File(uploadPath + fileName));
        Catalog catalog1 = catalogServiceIMPL.findById(catalog);
        productServiceIMPL.save(new Product(name, catalog1, fileName, quantity, title, priceP, status));
        return "redirect:getAll";

    }
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam int id, Model model) throws SQLException {
        Product editProduct = productServiceIMPL.findById(id);
        List<Catalog> listCatalog = catalogServiceIMPL.findAll();
        model.addAttribute("listCatalog", listCatalog);
        ModelAndView mv = new ModelAndView("product/edit_product");
        mv.addObject("product", editProduct);
        return mv;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("product") Product product,
                         @RequestParam("idC") int idcata,
                         @RequestParam("image") MultipartFile image) throws SQLException, IOException {
        String uploadPath = "D:\\MD4\\Bai_Tap_MD4\\end_md4neww\\src\\main\\resources\\assets\\image";
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = image.getOriginalFilename();
        if (fileName.equals("")){
            fileName = productServiceIMPL.findById(product.getIdP()).getUrlImageP();
        }else {
            FileCopyUtils.copy(image.getBytes(), new File(uploadPath + fileName));
        }
        Catalog catalog = catalogServiceIMPL.findById(idcata);
        product.setCatalog(catalog);
        product.setUrlImageP(fileName);
        productServiceIMPL.update(product);
        return "redirect:getAll";
    }

}
