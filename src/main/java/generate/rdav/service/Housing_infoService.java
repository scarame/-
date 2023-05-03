package generate.rdav.service;

import generate.rdav.entity.Housing_info;
import com.baomidou.mybatisplus.extension.service.IService;
import generate.rdav.entity.ParamList;

import java.util.List;
import java.util.Map;


/**
 *
 */
public interface Housing_infoService extends IService<Housing_info> {
    Map getRentList (ParamList paramList);
    Housing_info selectById(int id);
    List<Housing_info> recommend();
    List<Housing_info> search(String s);
}
