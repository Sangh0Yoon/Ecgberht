package ecgberht;

public class ChangeMapType_compareWithB0MVE extends ChangeMapType{
    public ChangeMapType_compareWithB0MVE(int map_height, int map_width, String[][] mapping){
        map = mapping;
        height = map_height;
        width = map_width;

        for (int map_x = height - 1; map_x > 0; map_x--) {
            for (int map_y = width - 1; map_y > 0; map_y--) {
                boolean condition_compareWithB0MVE = ( map[map_x][map_y].equals("B") || map[map_x][map_y].equals("0") || map[map_x][map_y].equals("M")
                                                || map[map_x][map_y].equals("V") || map[map_x][map_y].equals("E") );
                if ( condition_compareWithB0MVE ) {
                    if (map[map_x - 1][map_y].equals("6")) map[map_x - 1][map_y] = "1";
                    if (map[map_x][map_y - 1].equals("6")) map[map_x][map_y - 1] = "1";
                    if (map[map_x - 1][map_y - 1].equals("6")) map[map_x - 1][map_y - 1] = "1";
                }
            }
        }
    }
}