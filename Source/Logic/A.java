package Logic;

import java.lang.Math.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Vector;

public class A {
    public static int heuristics(Coords a, Coords b)
    {

        return Math.abs(a.x-b.x)+(a.y-b.y);
    }

    public static Vector<Coords> minimum(ArrayList<Vector<Coords>> Way, Coords end)
    {
        int minCost=1000000;
        Vector<Coords> temp =new Vector<Coords>();
        Vector<Coords> vector =new Vector<Coords>();

        for(int it=0; it<Way.size(); it++ )  //szukanie minimum
        {
            vector=Way.get(it);
            if (vector.size() + heuristics(end, vector.lastElement()) < minCost)
            {
                minCost=vector.size() + heuristics(end, vector.lastElement());
                temp=vector;
            }

        }

        return temp;
    }



    public static void A()
    {
        ArrayList<Vector<Coords>> Way=new ArrayList<Vector<Coords>>();
        Coords start, end;
        start = new Coords();
        end = new Coords();

        for (int i=0; i< InitMap.x; i++)  // wspolrzedne poczatku i konca
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

        Vector<Coords> vector =new Vector<Coords>();
        Vector<Coords> temp =new Vector<Coords>();
        vector.add(start);
        Way.add(vector);
        int minCost=1000000;

        while(1==1) {  //szukanie do bolu


           temp = minimum(Way,end);
            
            System.out.println(minCost);





            break;

        }

        InitMap.resetFlag();
    }
}
