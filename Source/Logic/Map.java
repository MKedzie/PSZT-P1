public class Map {
    protected FieldTypes[][] MapTiles;

    protected int mapSizeX;
    protected int mapsizeY;

    public Map(int sizeX, int sizeY) {
        this.MapTiles = new FieldTypes[sizeX][sizeY];
        this.mapSizeX = sizeX;
        this.mapsizeY = sizeY;
        for (FieldTypes[] row : this.MapTiles) {
            for (FieldTypes field : row) {
                field = FieldTypes.Free;
            }

        }
    }

    public int getMapSizeX() {
        return mapSizeX;
    }

    public int getMapSizeY() {
        return mapsizeY;
    }



    public FieldTypes[][] getMapTiles() {
        return MapTiles;
    }

    public void setMapTiles(FieldTypes[][] mapTiles) {
        MapTiles = mapTiles;
    }

    public void setTile(int row, int column, FieldTypes type) {
        this.MapTiles[row][column] = type;
    }//todo
    //funkcja upośledzona - umożliwia wiele wejść, wiele wyjść


    public FieldTypes getField(int x, int y) {
        return this.MapTiles[x][y];
    }
    // public void resizeMap(int newX, int newY){} // todo

}
