package generate.rdav.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generate.rdav.entity.User;
import generate.rdav.service.UserService;
import generate.rdav.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public User login(String account, String passWord) {
        return userMapper.login(account,passWord);
    }
    @Override
    public void register(String account, String passWord,String phone){
        userMapper.register(account,passWord,phone);

    }
    @Override
    public boolean is_repeated(String account) {
        return  null!=userMapper.is_repeated(account);
    }
    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
}




