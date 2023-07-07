package ra.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.catalog.Catalog;
import ra.model.service.catalog.CatalogServiceIMPL;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/catalogController")
public class CatalogController {
    CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
    @GetMapping("/catalog")
    public String showCatalog(){
        return "redirect:getAll";
    }
    @GetMapping("/getAll")
    public String getAll(Model model) throws SQLException {
        List<Catalog> listCatalog = catalogServiceIMPL.findAll();
        model.addAttribute("listCatalog", listCatalog);
        return "catalog";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam int id) throws SQLException {
        catalogServiceIMPL.delete(id);
        return "redirect:getAll";
    }
    @GetMapping("/add")
    public ModelAndView add (){
        return new ModelAndView("createCatalog","newCatalog", new Catalog());
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("newCatalog")Catalog c, Model model) throws SQLException {
        catalogServiceIMPL.save(c);
        return "redirect:getAll";
    }
    @GetMapping("/edit")
    public  ModelAndView edit (@RequestParam int id ) throws SQLException {
        Catalog editCatalog = catalogServiceIMPL.findById(id);
        ModelAndView mv = new ModelAndView("editCatalog");
        mv.addObject("catalog",editCatalog);
        return mv;
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("catalog")Catalog ca,  Model model) throws SQLException {
        catalogServiceIMPL.update(ca);
        return "redirect:getAll";

    }
}
