package generate.rdav.Controller;
import generate.rdav.entity.Housing_info;
import generate.rdav.entity.ParamList;
import generate.rdav.entity.Res;
import generate.rdav.service.UserService;
import generate.rdav.service.Housing_infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("rentalInfo")
public class RentalInformationController {
    @Autowired
    Housing_infoService housingInfoService;
    @Autowired
    UserService userServiceImpl;
    @PostMapping("getInfoList")
    public Res getRentalInfoList(@RequestBody ParamList paramList){
        return Res.success(housingInfoService.getRentList(paramList));
    }
    @PostMapping("getDetail")
    public Housing_info getHouse(@RequestParam int id){
        return housingInfoService.selectById(id);
    }
    @PostMapping("recommend")
    public Res recommend(){
        return Res.success(housingInfoService.recommend());
    }
    @RequestMapping("search")
    public Res search(String s){
        s='%'+s+'%';
        return Res.success(housingInfoService.search(s));
    }
}