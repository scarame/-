package generate.rdav.mapper;

import generate.rdav.entity.Hosinginfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity .entity.Hosinginfo
 */
@Mapper
@Repository
public interface HosinginfoMapper extends BaseMapper<Hosinginfo> {
    @Select("select * from hosinginfo")
    List<Hosinginfo> List ();
    @Insert("insert into hosinginfo (rent,position,houseType," +
            "buildingOrientation,decorateSituation,platformWebsite,phone,area,floor,maintenanceDate) " +
            "values(#{rent},#{position},#{houseType}," +
            "#{buildingOrientation},#{decorateSituation},#{platformWebsite},#{phone},#{area},#{floor},#{maintenanceDate})")
    int InsertInfo (Hosinginfo hosinginfo);
    @Select("select  count(*) from hosinginfo where position like #{address}")
    int addressNum(String address);
    @Select("select distinct position from hosinginfo where position regexp CONCAT('.*',#{urban},'.*',#{street},'.*')")
    String []addressClassification(String urban,String street);


    @Select("select * from hosinginfo where id=#{id}")
    Hosinginfo sele(int id);
    @Update("update hosinginfo set statitionNum=#{statitionNum} where position like #{position}")
    void updateHosinginfo(int statitionNum,String position);
    @Insert("insert into housing_info (" +
            "rent," +
            "square," +
            "rentPerSquare," +
            "stationNum," +
            "releaseDate," +
            "PlatformWebsite," +
            "urbanArea," +
            "administrativeDistrict," +
            "street," +
            "houseType," +
            "orientation," +
            "floor" +
            ") values" +
            "(" +
            "#{rent}," +
            "#{square}," +
            "#{rentPerSquare}," +
            "#{stationNum}," +
            "#{releaseDate}," +
            "#{PlatformWebsite}," +
            "#{urbanArea}," +
            "#{administrativeDistrict}," +
            "#{street}," +
            "#{houseType}," +
            "#{orientation}," +
            "#{floor}" +
            ")")
    void inser(
            double rent
            ,double square
            ,double rentPerSquare
            ,int stationNum
            ,String releaseDate
            ,String PlatformWebsite
            ,String urbanArea
            ,String administrativeDistrict
            ,String street
            ,String houseType
            ,String orientation
            ,int floor
    );

}




