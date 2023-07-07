package ra.model.service.user;

import ra.model.entity.user.User;
import ra.model.entity.user.UserLogin;
import ra.model.service.IService;

public interface IUserService extends IService<User, Integer> {
    UserLogin login(User user);
//    UserLogin findCartByUser(Integer cartId);
    boolean updateStatusUser (Integer id, Integer status);
    boolean checkExistsUsername(String username);
    boolean checkExitsPhoneNumber(String phone_number);

    UserLogin findAllUser(UserLogin userLogin);
}
