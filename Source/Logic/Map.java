public class Map {
    protected FieldTypes[][] MapTiles;

    protected int mapSizeX;
    protected int mapsizeY;
    protected boolean exitExist;
    protected boolean entryExist;


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

    public String toString() {
        String mapString = "";
        for (int row = 0; row < mapsizeY; row++) {
            String temp = "";
            for (int column = 0; column < mapSizeX; column++) {
                FieldTypes field = this.MapTiles[column][row];
                if (this.MapTiles[column][row] == null || this.MapTiles[column][row] == FieldTypes.Way)
                    temp = temp.concat("0");
                else if (this.MapTiles[column][row] == FieldTypes.Wall) temp = temp.concat("1");
                else if (this.MapTiles[column][row] == FieldTypes.Enter) temp = temp.concat("2");
                else temp = temp.concat("3");
            }
            temp = temp.concat("4");
            mapString = mapString.concat(temp);
        }
        return mapString;
    }

    public void fromString(String mapString) {
        long rowsLong = mapString.chars().filter(ch -> ch == '4').count();
        int rows = (int) rowsLong;
        int columns = (mapString.length() / rows) - 1;
        this.mapSizeX = columns;
        this.mapsizeY = rows;
        FieldTypes[][] temporary = new FieldTypes[columns][rows];
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                char thisPosition = mapString.charAt((y * (columns + 1) + x));
                if (thisPosition == '0') temporary[x][y] = FieldTypes.Free;
                else if (thisPosition == '1') temporary[x][y] = FieldTypes.Wall;
                else if (thisPosition == '2') temporary[x][y] = FieldTypes.Enter;
                else if (thisPosition == '3') temporary[x][y] = FieldTypes.Exit;
            }
        }

        this.MapTiles = temporary;
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

    public void resetMap() {
        for (int row = 0; row < this.mapsizeY; row++) {
            for (int column = 0; column < this.mapSizeX; column++) {
                if (this.MapTiles[column][row] == FieldTypes.Enter || this.MapTiles[column][row] == FieldTypes.Exit || this.MapTiles[column][row] == FieldTypes.Way)
                    this.MapTiles[column][row] = FieldTypes.Free;
            }
        }
    }


    public FieldTypes getField(int x, int y) {
        return this.MapTiles[x][y];
    }
    // public void resizeMap(int newX, int newY){} // todo

}
