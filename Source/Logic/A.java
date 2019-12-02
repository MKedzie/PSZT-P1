
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

public class A {

    static BiFunction<Coords, Coords, Double> heuristicsFunction;

    public static double heuristics(Coords a, Coords b)
    {
        return A.heuristicsFunction.apply(a, b).doubleValue();
    }

    public void setHeuristicsFunction(BiFunction<Coords, Coords, Double> heuristicsFunction) {
        this.heuristicsFunction = heuristicsFunction;
    }

    public static boolean result = false;

    public static Vector<Coords> minimum(ArrayList<Vector<Coords>> Way, Coords end)
    {
        double minCost=1000000;
        Vector<Coords> temp =new Vector<Coords>();
        Vector<Coords> vector;

        for(int it=0; it<Way.size(); it++ )  //szukanie minimum
        {
            vector=Way.get(it);
            if (vector.size() + heuristics(end, vector.lastElement()) < minCost)
            {
                minCost=vector.size() + heuristics(end, vector.lastElement());
                temp=vector;
            }

        }
      //  System.out.println("Minimum: "+minCost);
        return temp;
    }



    public static Vector<Coords> A()
    {  // int maxIterations=100000;
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


        vector.add(start);
        Way.add(vector);

        int minCost=1000000;
        int k=0;
        while(k<InitMap.maxIterations) {  //szukanie do bolu
            k++;
            temp = minimum(Way,end);
            Way.remove(temp);
            Coords current=temp.lastElement();

           for (int i =-1; i<2 ; i++)
           {
               for (int j =-1; j<2 ; j++) {
                   if(j==0 && i==0)continue;
                   if((current.x + i)>=0 && (current.y + j)>=0 && (current.x + i)<=InitMap.x-1 && (current.y + j)<=InitMap.y-1) // jesli nie wychodzimy poza mape
                   {


                       if(InitMap.Mapa[current.x+i][current.y+j]==3) // jesli znalezlismy koniec
                       {
                        vector=temp;
                        vector.remove(start);
                           A.result = true;
                           System.out.println(k);
                        return vector;
                       }
                       else
                       {
                           if(InitMap.Mapa[current.x+i][current.y+j]==0)
                           {
                            AtomicInteger flag= new AtomicInteger(0);
                            Coords next=new Coords(current.x+i,current.y+j);

                                temp.forEach(iterator -> {
                                    if(iterator.x==next.x && iterator.y==next.y) {
                                        flag.set(1);

                                    }
                                    Way.forEach(list ->{
                                        if(list.lastElement().x==next.x && list.lastElement().y==next.y)
                                            flag.set(1);
                                    });

                                });
                                if (flag.get()==1)
                                    {
                                        flag.set(0);
                                        continue;

                                    }
                                else
                                    {
                                        Vector<Coords> newVector = (Vector) temp.clone();
                                        newVector.add(next);
                                        Way.add(newVector);
                                    }

                           }

                       }
                   }

               }
           }

        }
        if (k==InitMap.maxIterations){
            System.out.println("Nie mozna znalezc sciezki w podanej liczbie iteracji");
            vector.removeAllElements();

        }
        return vector;
    }
}
