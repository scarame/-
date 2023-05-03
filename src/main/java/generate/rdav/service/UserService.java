package generate.rdav.service;

import generate.rdav.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {
    User login(String account,String passWord);
    void register(String account,String passWord,String phone);
    boolean is_repeated(String account);
    User getUserById(int id);
}
