package ecgberht;

public class ChangeMapType_compareWithOne extends ChangeMapType{
    public ChangeMapType_compareWithOne(int map_height, int map_width, String[][] mapping){
        map = mapping;
        height = map_height;
        width = map_width;

        for (int map_x = height - 1; map_x > 0; map_x--) {
            for (int map_y = width - 1; map_y > 0; map_y--) {
                boolean condition_compareWithOne = map[map_x][map_y].equals("1");
                if ( condition_compareWithOne ) {
                    if (map[map_x - 1][map_y].equals("6")) map[map_x - 1][map_y] = "2";
                    if (map[map_x][map_y - 1].equals("6")) map[map_x][map_y - 1] = "2";
                    if (map[map_x - 1][map_y - 1].equals("6")) map[map_x - 1][map_y - 1] = "2";
                }
            }
        }
    }
}