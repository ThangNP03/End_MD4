package ra.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index( ){
        return "/index";
    }
    @GetMapping("/profile")
    public String profile(){
        return "profile/profile_view";
    }

}
