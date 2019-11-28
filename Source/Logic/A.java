package Logic;


import org.ini4j.Ini;

public class A {
    public static void A()
    {
        Coords start, end;
        start = new Coords();
        end = new Coords();

        for (int i=0; i< InitMap.x; i++)
        {
            for (int j=0; j<InitMap.y; j++)
            {
                if(InitMap.Mapa[i][j]==3)
                {
                    end.x=i;
                    end.y=j;
                }
                if(InitMap.Mapa[i][j]==1)
                {
                    start.x=i;
                    start.y=j;
                }

            }
        }

        System.out.println(end.x+" "+end.y+" "+start.x+" "+start.y);





        InitMap.resetFlag();
    }
}
