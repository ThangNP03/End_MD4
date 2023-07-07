package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.order.OrderDetail;
import ra.model.entity.user.User;
import ra.model.entity.user.UserLogin;
import ra.model.service.odder.CartServiceIMPL;
import ra.model.service.user.IUserService;
import ra.model.service.user.UserServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;


@Controller
public class UserController {

    private IUserService userService = new UserServiceIMPL();



    @GetMapping("/getAll")
    public String showUser(Model model) throws SQLException {
        List<User> listUser = userService.findAll();
        model.addAttribute("listUser", listUser);
        return "user/uer_view";
    }
    @GetMapping("/login")
    public String showFormLogin(){
        return "/form_login";
    }
    @GetMapping("/register")
    public String showFormRegister(){
        return  "/form_register";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest request){

        if(username.trim().equals("")|| password.trim().equals("")){
            model.addAttribute("login","Username or Password Not Required!!!");
            return "form_login";
        }

        UserLogin userLogin = userService.login(new User(username, password));

        System.out.println(userLogin);


        if (userLogin == null ){
            model.addAttribute("login","Username or password incorrect!!!");
            return "form_login";
        }
        else if (userLogin.getStatus() != 1){
            model.addAttribute("loginStatus","Your account has been locked!!!");
            return "form_login";
        }
        else {
                model.addAttribute("userLogin", userLogin);
                System.out.println(userLogin);
                request.getSession().setAttribute("userLogin", userLogin);
                if (userLogin.getRole() == 1){
                    System.out.println("user đang đăng nhập "+userLogin);
                    // chuyển đên trang admin
                    return "/admin";
                }else if(userLogin.getRole() == 0){
                    // Chuyển đên trang người dùng
                    return "/index";
                }else {
                    return "/index";
                }
            }

        }


    @PostMapping("/update-status/{userId}")
    public String updateStatus(@PathVariable("userId") Integer userId, Integer status) {
        boolean success = userService.updateStatusUser(userId,status);
        if (success) {
            return "redirect:/getAll";
        } else {
            return "redirect:/getAll";
        }
    }




    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("userLogin");
        return "/index";
    }
    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("fullName") String fullName,
                           @RequestParam("address") String address,
                           @RequestParam("phone_number") String phone_number,
                           @RequestParam("password") String password,
                           Model model) throws SQLException {
        System.out.println(username);
        System.out.println(password);
        if (username.trim().equals("")){
            model.addAttribute("username","User   Password Not Required");
            return "form_register";
        }else if (userService.checkExistsUsername(username)){
            model.addAttribute("username","Username is Exist");
            return "form_register";
        }
        if (phone_number.trim().equals("")) {
            model.addAttribute("phone_number","Phone number Not Required");
            return "form_register";
        }else if (userService.checkExitsPhoneNumber(phone_number)){
            model.addAttribute("phone_number","Phone number is Exist");
            return "form_register";
        }
        if (address.trim().equals("")) {
            model.addAttribute("fullName","Address  Not Required");
            return "form_register";
        }
        if (fullName.trim().equals("")) {
            model.addAttribute("fullName","Full Name  Not Required");
            return "form_register";
        }
        if (password.trim().equals("")) {
            model.addAttribute("password","Password Not Required");
            return "form_register";}
        if (!isValidPassword(password)) {
            model.addAttribute("passwordCheck","Password must be more than 6 characters");
            return "form_register";}
        userService.save(new User(username, fullName,phone_number,address,password ));
        return "/form_login";}

    private boolean isValidPassword(String password) {
        String regex = "^(\\w){6,}$";
        return password.matches(regex);
    }

}
