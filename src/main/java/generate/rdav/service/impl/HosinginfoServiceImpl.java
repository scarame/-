package generate.rdav.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generate.rdav.entity.Hosinginfo;
import generate.rdav.service.HosinginfoService;
import generate.rdav.mapper.HosinginfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class HosinginfoServiceImpl extends ServiceImpl<HosinginfoMapper, Hosinginfo>
    implements HosinginfoService{
    @Autowired HosinginfoMapper hosinginfoMapper;
    @Override
    public List<Hosinginfo> List() {
        return hosinginfoMapper.List();
    }

    @Override
    public int  insertHoseInfo(Hosinginfo hosinginfo) {
        hosinginfoMapper.InsertInfo(hosinginfo);
        return 1;
    }
    @Override
    public int addressNum(String address) {
        return hosinginfoMapper.addressNum(address);
    }
    @Override
    public String[] addressClassification(String urbanArea, String street) {
        return hosinginfoMapper.addressClassification(urbanArea,street);
    }
}




