package generate.rdav.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generate.rdav.entity.Housing_info;
import generate.rdav.entity.ParamList;
import generate.rdav.service.Housing_infoService;
import generate.rdav.mapper.Housing_infoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class Housing_infoServiceImpl extends ServiceImpl<Housing_infoMapper, Housing_info>
    implements Housing_infoService{
    @Autowired Housing_infoMapper housing_infoMapper;
    @Override
    public Map getRentList(ParamList paramList) {
        String limit=paramList.getRow()*paramList.getPage()+","+paramList.getRow();
        //排序方式
        String sortBy="";
        Map<Object,Object[]> map=new HashMap<>();
        map.put(paramList.getSortWeight().getRent()*10+1,new Object[]{"rent",paramList.getSortord().isRent()});
        map.put(paramList.getSortWeight().getFloor()*10+5,new Object[]{"floor",paramList.getSortord().isFloor()});
        map.put(paramList.getSortWeight().getRentPS()*10+2,new Object[]{"rentPerSquare",paramList.getSortord().isRentPS()});
        map.put(paramList.getSortWeight().getSquare()*10+4,new Object[]{"square",paramList.getSortord().isSquare()});
        map.put(paramList.getSortWeight().getStationN()*10+3,new Object[]{"stationNum",paramList.getSortord().isStationN()});

        int flag,MapSize=map.size();
        for(int i=0;i<MapSize;i++){
            flag=100;
            for(Object key:map.keySet()){
                if((int)key<flag){
                    flag=(int)key;
                }
            }

            sortBy+=map.get(flag)[0];
            if((boolean)map.get(flag)[1])
                sortBy+=" asc";
            else
                sortBy+=" desc";
            //System.out.println(flag);
            if((i+1)<MapSize)
                sortBy+=",";
            map.remove(flag);
        }

        //筛选条件
        String condition=" ";
        condition+=paramList.getRminValue()+"<=rent and rent<="+paramList.getRmaxValue()+" and ";
        condition+=paramList.getSminValue()+"<=square and square<="+paramList.getSmaxValue()+" and ";
        condition+=paramList.getStationNum()+"<=stationNum and "+paramList.getRentPerSquare()+">=rentPerSquare and " +
                "floor>="+paramList.getFloor();
        if(!(paramList.getOrientation()).equals("")) condition+=" and orientation='"+paramList.getOrientation()+"'";
        if(!(paramList.getHouseType().getBedroom()==0&&paramList.getHouseType().getLivingRoom()==0&&paramList.getHouseType().getRestRoom()==0)){
            condition+=" and houseType  like'";
            if(!(paramList.getHouseType().getBedroom()==0))condition+=paramList.getHouseType().getBedroom()+"室";
            else condition+="%";
            if(!(paramList.getHouseType().getLivingRoom()==0))condition+=paramList.getHouseType().getLivingRoom()+"厅";
            else condition+="%";
            if(!(paramList.getHouseType().getRestRoom()==0))condition+=paramList.getHouseType().getRestRoom()+"卫";
            else condition+="%";
            condition+="'";
        }

        String sql="SELECT * FROM housing_info where"+condition+" ORDER BY "+sortBy+" LIMIT "+limit;
        int infoTotal=housing_infoMapper.infoCount(condition);
        Map<String,Object> result=new HashMap<>();
        result.put("infoList",housing_infoMapper.infoList(sql));
        result.put("infoTotal",infoTotal);
        return result;
    }

    @Override
    public Housing_info selectById(int id) {
        return housing_infoMapper.selectById(id);
    }

    @Override
    public List<Housing_info> recommend() {
        return housing_infoMapper.recommend();
    }

    @Override
    public List<Housing_info> search(String s) {
        return housing_infoMapper.search(s);
    }
}




