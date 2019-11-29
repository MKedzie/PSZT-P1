package Logic;

import org.apache.velocity.Template;

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



    public static Vector<Coords> A()
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
        Vector<Coords> temp;
        Vector<Coords> check;


        vector.add(start);
        Way.add(vector);
        int minCost=1000000;

        while(1==1) {  //szukanie do bolu

            temp = minimum(Way,end);
            Way.remove(temp);
            Coords current=temp.lastElement();

           for (int i =-1; i<2 ; i++)
           {
               for (int j =-1; j<2 ; j++) {
                   if(j==0 && i==0)continue;
                   if((current.x + i)>=0 && (current.y + j)>=0 && (current.x + i)<=InitMap.x-1 && (current.y + j)<=InitMap.y-1) // jesli nie wychodzimy poza mape
                   {
                       System.out.println("nowa iteracja");
                       System.out.println(current.x+" "+current.y);
                       System.out.println((current.x+i)+" "+(current.y+j));

                       if(InitMap.Mapa[current.x+i][current.y+j]==3) // jesli znalezlismy koniec
                       {
                        vector=temp;
                        InitMap.resetFlag();
                        System.out.println("Wybrana sciezka");
                        vector.forEach(coords -> System.out.println(coords.x+" "+coords.y));
                        return vector;
                       }
                       else
                       {
                           if(InitMap.Mapa[current.x+i][current.y+j]==0)
                           {
                            Vector<Coords> newVector =(Vector)temp.clone();
                            Coords next=new Coords(current.x+i,current.y+j);
                            newVector.add(next);
                            Way.add(newVector);
                           }

                       }
                   }

               }
           }

        }

    }
}
