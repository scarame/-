package generate.rdav.Controller;

import generate.rdav.entity.Res;
import generate.rdav.entity.User;
import generate.rdav.service.UserService;
import generate.rdav.util.CONSTANT;
import generate.rdav.util.JwtUtil;
import generate.rdav.util.md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.RedisTemplate;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private RedisTemplate redisTemplate;
    @PostMapping("login")
    public Res<Object> login(HttpServletRequest request, String account, String password){

        //获取用户对象和请求ip
        try {
            User user =  userServiceImpl.login(account, md5.getMD5String(password));
            if(!user.equals(null)){
                //创建token；
                String token= JwtUtil.createJWT(user);
                //设置token有效期和键值；
                redisTemplate.opsForValue().set(account,token,CONSTANT.count,CONSTANT.C_TimeUnit);
                //设置返回值；
                Map<String,Object> map=new HashMap<>();
                map.put("userInfo",user);
                map.put("token",token);
                return Res.success("login successfully",map);
            }
        }catch (Exception e){
            Res.fail("错误请求");
        }

        return Res.fail("login failure");
    }
    @PostMapping("logout")
    public Res logout(String account){
        redisTemplate.delete(account);
        return Res.success("账号已登出",true);
    }
    @PostMapping("register")
    public Res Register(String account , String password ,String phone){
        if(account.trim().equals("")||password.trim().equals("")){
            return Res.fail("missing parameter");
        }else if(userServiceImpl.is_repeated(account)){
            return Res.fail("the account has been registered");
        }
        userServiceImpl.register(account,md5.getMD5String(password),phone);
        return Res.success("registered successfully",true);
    }
    @PostMapping("getUserById")
    public Res getUserById(int id){
        return Res.success(userServiceImpl.getUserById(id));
    }
}
