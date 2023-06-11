package ecgberht;

public class ChangeMapType_compareWithFour extends ChangeMapType{
    public ChangeMapType_compareWithFour(int map_height, int map_width, String[][] mapping){
        map = mapping;
        height = map_height;
        width = map_width;

        for (int map_x = height - 1; map_x > 0; map_x--) {
            for (int map_y = width - 1; map_y > 0; map_y--) {
                boolean condition_compareWithFour = map[map_x][map_y].equals("4");
                if ( condition_compareWithFour ) {
                    if (map[map_x - 1][map_y].equals("6")) map[map_x - 1][map_y] = "4";
                    if (map[map_x][map_y - 1].equals("6")) map[map_x][map_y - 1] = "4";
                    if (map[map_x - 1][map_y - 1].equals("6")) map[map_x - 1][map_y - 1] = "4";
                }
            }
        }
    }
}

