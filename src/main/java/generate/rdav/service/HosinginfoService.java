package generate.rdav.service;

import generate.rdav.entity.Hosinginfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface HosinginfoService extends IService<Hosinginfo> {
    List<Hosinginfo> List();
    int insertHoseInfo(Hosinginfo hosinginfo);
    int addressNum(String address);
    String[]addressClassification(String urbanArea,String street);
}
