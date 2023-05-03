package generate.rdav.Controller;

import generate.rdav.entity.Res;
import generate.rdav.service.HosinginfoService;
import generate.rdav.util.UrlCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("ChartData")
public class Controller {
    @Autowired
    HosinginfoService hosinginfoService;
    @RequestMapping("graph")
    public String graph(){
        return "ok";
    }
    @RequestMapping("crawl")
    public void runCrawler(){
        UrlCrawler.crawler2("https://zh.zu.ke.com",58);
    }
    @RequestMapping("queryQuantity")
    public Res queryQuantity(String ua,String stre){
        Map<String,Object> map=new HashMap<>();
        LinkedHashSet<String> hashSet=new LinkedHashSet<>();
        int num,partialSum=0,sum=0;
        String [] strArr= hosinginfoService.addressClassification(ua,stre);
        for(String s :strArr){
           s=s.replaceAll("([一二三四五六七八九壹贰叁肆伍陆柒捌玖期园号]+)$","");
           s="%"+s.replaceAll("[-]","%")+"%";
           hashSet.add(s);
        }
        for(String s:hashSet){//detailed address
            num=hosinginfoService.addressNum(s);
            sum=sum+num;
            if(num>=5){
                map.put(s,num);
                partialSum=partialSum+num;
            }
        }
        return Res.success("sum="+sum+"partialSum="+partialSum,map);
    }

    @RequestMapping("getAddress")
    public void getAddress(){

    }
}
