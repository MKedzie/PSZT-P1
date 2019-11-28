package Logic;


public class InitMap {

    public static int Mapa[][];
    public static int x;
    public static int y;
    private static int flag;

    public static void setFlag()
    {
        flag=1;
    }

    public static void resetFlag()
    {
        flag=0;
    }

    public static int getFlag()
    {
        return flag;
    }

    public static void show()
    {
        System.out.println("x: "+x+" y: "+y+" flag: "+flag);
    }

}

