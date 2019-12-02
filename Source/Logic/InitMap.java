

public class InitMap {

    public static void importMap(Map mapa) {
        InitMap.x = mapa.getMapSizeX();
        InitMap.y = mapa.getMapSizeY();
        InitMap.Mapa = new int[InitMap.x][InitMap.y];
        for (int row = 0; row < InitMap.y; row++) {
            for (int column = 0; column < InitMap.x; column++) {
                FieldTypes temp = mapa.getField(column, row);
                if (temp == null) InitMap.Mapa[column][row] = 0;
                else {
                    switch (mapa.getField(column, row)) {
                        case Exit:
                            InitMap.Mapa[column][row] = 3;
                            break;

                        case Wall:
                            InitMap.Mapa[column][row] = 2;
                            break;

                        case Enter:
                            InitMap.Mapa[column][row] = 1;
                            break;

                        default:
                            InitMap.Mapa[column][row] = 0;
                            break;
                    }
                }

            }
        }

    }

    public static int Mapa[][];
    public static int x;
    public static int y;
    public static int maxIterations=10000;
    public static void setMaxIter(int a){
        maxIterations=a;}



}
