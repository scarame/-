package generate.rdav.Controller;

import generate.rdav.entity.Hosinginfo;
import generate.rdav.mapper.HosinginfoMapper;
import generate.rdav.util.infoCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transition")
public class transition {
    @Autowired HosinginfoMapper hosinginfoMapper;
    @RequestMapping("catch")
    public void graph(){
        Hosinginfo house;
        for(int i=627;i<=2810;i++){
            house=hosinginfoMapper.sele(i);
            if(house==null){
                continue;
            }
            //租金
            double rent;
            try{
                rent=Integer.parseInt(house.getRent());
            }catch (Exception e){
                String r[] =house.getRent().split("[-]");
                rent=(Integer.parseInt(r[0])+Integer.parseInt(r[1]))/2;
            }
            //面积
            double square;
            house.setArea(house.getArea().replaceAll("[㎡]",""));
            try{
                square=Double.parseDouble(house.getArea());
            }catch (Exception e){
                String a[] =house.getArea().split("[-]");
                square=(Integer.parseInt(a[0])+Integer.parseInt(a[1]))/2;
            }
            //每平米租金
            double rentPerSquare=rent/square;
            //发布时间
            String releaseDate=house.getMaintenanceDate();
            //网址
            String PlatformWebsite=house.getPlatformWebsite();
            //房型
            String houseType=house.getHousetype();
            //朝向
            String orientation=house.getBuildingOrientation();
            //楼层
            int floor;
            try{
                floor=Integer.parseInt(house.getFloor().replaceAll("[层]",""));
            }catch (NumberFormatException e){
                floor=-1;
            }



            //地址
            String administrativeDistrict,urbanArea,street;
            try {
                String []p=house.getPosition().split("-");
                 administrativeDistrict=p[0];
                 urbanArea=p[1];
                 street=p[2];
            }catch (Exception e){
                administrativeDistrict= infoCrawler.analyseString(house.getPosition(),".{2}[区]");
                urbanArea=house.getPosition().replaceAll("^[广东省珠海市]*.{2}区","");
                street=house.getPosition().replaceAll("^[广东省珠海市]*.{2}区","");
            }
            //站点数量
            int stationNum=house.getStatitionNum();
            hosinginfoMapper.inser(rent,square,rentPerSquare,stationNum,releaseDate,PlatformWebsite,urbanArea,administrativeDistrict
            ,street,houseType,orientation,floor);
        }
    }
    @RequestMapping("setNum")
    public void setStationN(){
        for(int i=0;i<urbanArealist.length;i++){
            hosinginfoMapper.updateHosinginfo(stationNum[i],urbanArealist[i]);
        }
    }
    public static String []urbanArealist={
            "%香洲区%翠前%",
            "%香洲区%兰埔%",
            "%香洲区%前山%",
            "%香洲区%拱北%",
            "%香洲区%上冲%",
            "%香洲区%吉大%",
            "%香洲区%南屏%",
            "%香洲区%夏湾%",
            "%香洲区%翠微%",
            "%香洲区%洪湾%",
            "%香洲区%老香洲%",
            "%香洲区%南湾%",
            "%香洲区%柠溪%",
            "%香洲区%新香洲%",
            "%香洲区%湾仔%",
            "%香洲区%华发新城%",
            "%香洲区%华发世纪城%",
            "%香洲区%中信红树湾%",

            "%斗门区%白藤头%",
            "%斗门区%井岸镇%",
            "%斗门区%红旗镇%",
            "%斗门区%斗门镇%",
            "%斗门区%白蕉镇%",
            "%斗门区%西江月%",
            "%斗门区%美湖湾%",
            "%斗门区%家和城%",
            "%斗门区%奥园香海美景%",
            "%斗门区%招商依云水岸%",
            "%斗门区%时代倾城双生花%",
            "%斗门区%海逸豪庭%",
            "%斗门区%中邦御珑湾%",
            "%斗门区%时代倾城%",

            "%金湾区%三灶镇%",
            "%金湾区%融创%",
            "%金湾区%红旗镇%",
            "%金湾区%平沙镇%",
            "%金湾区%航空新城%",
            "%金湾区%龙光%",
            "%金湾区%万科城市花%",
            "%金湾区%山海一品海岸花%",
            "%金湾区%碧桂园华发海湾壹%",
            "%金湾区%九洲保利天和%",
            "%金湾区%奥园天悦湾%",
            "%金湾区%万科海上城市%",
            "%金湾区%时代山湖海南湾花%",
            "%金湾区%翰林金寓%",
            "%金湾区%保利香槟国际花%",
            "%金湾区%龙湖原著台%",
            "%金湾区%中海左岸岚庭%",
            "%金湾区%金地格林春晓%",
            "%金湾区%湖城大境广场%",
            "%金湾区%旭日海岸%",
            "%金湾区%珠海碧桂园华发滨海天际%",
            "%金湾区%保利时代%",
            "%金湾区%华发国际商务中心%",
            "%金湾区%中海十里观澜%",
            "%金湾区%时代山湖海%",

            "%横琴区%横琴%",
            "%横琴区%璞醍海%",
            "%横琴区%龙光%",
            "%横琴区%保利国际广场%",
            "%横琴区%金融传媒中心%",
            "%横琴区%国贸大厦%",
            "%横琴区%万象世界%",
            "%横琴区%荔枝湾花园%",
            "%横琴区%华发广场%",
            "%横琴区%保利国际广场%",
            "%横琴区%中海名钻花园%",
            "%横琴区%银鑫花园%",
            "%横琴区%三一南方%",
            "%横琴区%湾区一号%",
            "%横琴区%华发悦府%",
            "%横琴区%富力中心%",
            "%横琴区%铁建大厦%",
            "%横琴区%横琴新区%",

            "%高新区%唐家湾%",
            "%高新区%格力海岸%",
            "%高新区%万科红树东岸%",
            "%高新区%首开龙湖天钜%",
            "%高新区%仁恒滨海半岛%",
            "%高新区%泰盈玖悦湾%",
            "%高新区%唐韵%",
            "%高新区%万科翡翠中央%",
            "%高新区%高新宝龙城%",
            "%高新区%海怡湾畔%",
            "%高新区%翠湖香山玉兰苑%",
            "%高新区%华发蔚蓝堡%",
            "%高新区%珠海雅居乐国际花园%",
            "%高新区%凤凰兰亭%",
            "%高新区%保利海上五月花%",
            "%高新区%远大美域%"
    };
    public static int stationNum[]={
            25,
            16,
            15,
            20,
            27,
            17,
            18,
            31,
            26,
            5,
            17,
            13,
            16,
            29,
            6,
            18,
            11,
            10,

            26,
            17,
            13,
            20,
            13,
            22,
            15,
            16,
            9,
            13,
            4,
            15,
            12,
            17,

            21,
            13,
            10,
            10,
            22,
            12,
            13,
            15,
            9,
            2,
            13,
            8,
            7,
            7,
            11,
            18,
            13,
            3,
            16,
            13,
            14,
            13,
            20,
            13,
            11,

            7,
            14,
            18,
            23,
            6,
            13,
            13,
            18,
            11,
            13,
            23,
            13,
            14,
            10,
            20,
            10,
            7,
            6,

            20,
            6,
            8,
            6,
            5,
            7,
            7,
            13,
            23,
            12,
            15,
            9,
            9,
            13,
            10,
            10
    };
}
