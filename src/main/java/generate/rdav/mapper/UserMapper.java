package generate.rdav.mapper;

import generate.rdav.entity.Housing_info;
import generate.rdav.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Entity .entity.User
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select account,phone from user where account=#{account} and password=#{password}")
    User login(String account , String password);
    @Insert("insert into user (account,password,phone) values(#{account},#{password},#{phone})")
    void register(String account ,String password,String phone);
    @Select("select account from user where account=#{account}")
    User is_repeated(String account);
    @Select("select account,phone from user where id=#{id}")
    User getUserById (int id);

}




