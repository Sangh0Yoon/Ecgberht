package ecgberht;

public class FactoryChangeMapType{
    public ChangeMapType getInstance(int height, int width, String condition, String[][] map){
        if(condition.equals("B0MVE")) return new ChangeMapType_compareWithB0MVE(height,width,map);
        else if(condition.equals("ONE")) return new ChangeMapType_compareWithOne(height,width,map);
        else if(condition.equals("TWO")) return new ChangeMapType_compareWithTwo(height,width,map);
        else if(condition.equals("THREE")) return new ChangeMapType_compareWithThree(height,width,map);
        return new ChangeMapType_compareWithFour(height,width,map);
    }
}