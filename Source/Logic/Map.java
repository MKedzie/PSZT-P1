public class Map {
    protected FieldTypes[][] MapTiles;

    public Map(int sizeX, int sizeY) {
        this.MapTiles = new FieldTypes[sizeX][sizeY];
        for (FieldTypes[] row : this.MapTiles) {
            for (FieldTypes field : row) {
                field = FieldTypes.Free;
            }

        }
    }


    public FieldTypes[][] getMapTiles() {
        return MapTiles;
    }

    public void setMapTiles(FieldTypes[][] mapTiles) {
        MapTiles = mapTiles;
    }


}
