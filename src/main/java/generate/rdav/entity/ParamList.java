package generate.rdav.entity;

import lombok.Data;

@Data
public class ParamList {
    int page=0;
    int row=20;
    double sminValue=0;
    double rminValue=0;
    int stationNum=0;
    double rmaxValue=1000000;
    double smaxValue=1000000;
    double rentPerSquare=100000;
    int floor=-1;
    String orientation="";

    HouseType houseType;
    SortWeight sortWeight;
    Sortord sortord;

    @Data
    public static class HouseType {
         int bedroom=0;
         int livingRoom=0;
         int restRoom=0;
    }
    @Data
    public static class Sortord {
        boolean rent=true;
        boolean square=false;
        boolean stationN=false;
        boolean rentPS=true;
        boolean floor=true;
    }
    @Data
    public static class SortWeight {
        int rent=1;
        int square=4;
        int stationN=3;
        int rentPS=2;
        int floor=5;
    }
}
