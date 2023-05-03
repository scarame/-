package generate.rdav.mapper;

import generate.rdav.entity.Hosinginfo;
import generate.rdav.entity.Housing_info;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import generate.rdav.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity .entity.Housing_info
 */
@Mapper
@Repository
public interface Housing_infoMapper extends BaseMapper<Housing_info> {

    @Select("SET @dsql=CONCAT(#{sql});\n" +
            "PREPARE stmt FROM @dsql;\n" +
            "EXECUTE stmt;")
    List<Housing_info> infoList (String sql);
    @Select("SET @dsql=CONCAT('select count(*) from housing_info where ',#{condition});\n" +
            "PREPARE stmt FROM @dsql;\n" +
            "EXECUTE stmt;")
    int infoCount (String condition);
    @Select("select * from housing_info where id=#{id}")
    Housing_info selectById (int id);
    @Select("select * from housing_info where rent<=4000 order by rentPerSquare asc,stationNum desc limit 0,8 ")
    List<Housing_info> recommend ();
    @Select("select * from housing_info where administrativeDistrict like#{s} or urbanArea like#{s} or street like#{s}")
    List<Housing_info> search (String s);
}




