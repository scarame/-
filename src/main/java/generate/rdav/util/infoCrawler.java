package generate.rdav.util;
import generate.rdav.entity.Hosinginfo;
import generate.rdav.service.HosinginfoService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class infoCrawler {
    @Autowired
    HosinginfoService hosinginfoService;

    private static infoCrawler iC;

    @PostConstruct
    public void init(){
        iC=this;
    }


    public  void contentCrawl(String baseUrl, String PageUrl){
        //页面对象，内涵页面所有元素信息
        try {
            Document document= Jsoup.connect(baseUrl+PageUrl).get();
            //获取房屋信息块
            Elements elements=document.select("div.content__list--item--main");
            //新建house类对象，用作传输
            Hosinginfo house=new Hosinginfo();
            for(Element element:elements){
                //详情页面
                String detailURl= (element.select("a")).get(0).attr("href");
                Document DetailDocument= Jsoup.connect(baseUrl+detailURl).get();
                String s1= DetailDocument.select("p.flat__info--subtitle").text();
                //列表页面
                String s=element.select("p.content__list--item--des").text();

                //----赋值---//
                //每月租金
                house.setRent(element.select("em").text());
                //详情网页
                house.setPlatformWebsite(baseUrl+detailURl);
                //电话
                house.setPhone(analyseString(s1,"[0-9]{8,11}"));
                //房屋类型
                house.setHousetype(analyseString(s,"\\d+室\\d+厅(\\d卫)?"));
                //面积
                house.setArea(analyseString(s,"\\d+\\.\\d+㎡"));
                //地址
                String position=analyseString(s,"[\\u4e00-\\u9fa5-]{4,}");
                if(position.equals("null")){
                    house.setPosition(analyseString(s1,"[\\u4e00-\\u9fa50-9]{6,}"));
                }else {
                    house.setPosition(analyseString(s,"[\\u4e00-\\u9fa5-]{4,}"));
                }
                //朝向
                house.setBuildingOrientation(analyseString(s,"[东南西北]"));
                //楼层
                house.setFloor(analyseString(s,"(\\d+层)"));
                //维护日期
                String m_date=element.select("span.content__list--item--time").text();
                m_date=analyseString(m_date,"[1-9今]+");
//                if(!m_date.equals("今")){
//                    house.setMaintenanceDate(Constant.changeDate_Date(-Integer.parseInt(m_date)));
//                }else{
//                    house.setMaintenanceDate(Constant.getCurrentDate());
//                }
                //装修情况
                house.setDecorateSituation(element.select("i.content__item__tag--decoration").text());

                //储存数据
                System.out.println(house);
                iC.hosinginfoService.insertHoseInfo(house);

            }
        }catch (Exception e){
            //e.printStackTrace();
            System.out.println("重复数据");
        }
    }
    @Test
    public  void CrawlerTest(){
        System.out.println("珠海市香洲区九洲大道中2118号".replaceAll("^.+区",""));
        ;

    }

    public static String analyseString(String str,String pattern){
        Pattern p=Pattern.compile(pattern);
        Matcher m=  p.matcher(str);
        while (m.find()){
            return m.group();
        }
        return "null";
    }
}
