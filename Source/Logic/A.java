
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
        double minCost=1000000; //inicjalizacja min kosztu
        Vector<Coords> temp =new Vector<Coords>();
        Vector<Coords> vector;

        for(int it=0; it<Way.size(); it++ )  //szukanie minimum
        {
            vector=Way.get(it);
            if (vector.size() + heuristics(end, vector.lastElement()) < minCost)  //jesli mniejsze niz koszt+heurystyka
            {
                minCost=vector.size() + heuristics(end, vector.lastElement());
                temp=vector;
            }

        }

        return temp; // ret minimum
    }



    public static Vector<Coords> A()
    {
        ArrayList<Vector<Coords>> Way=new ArrayList<Vector<Coords>>(); // lista przechowujaca vektory ktore sa scizkami
        Coords start, end; // Coords przechowuje tylko x,y - koordynaty
        start = new Coords();
        end = new Coords();

        for (int i=0; i< InitMap.x; i++)  // szukanie  wspolrzednych poczatku i konca
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

        Vector<Coords> vector =new Vector<Coords>(); //pierwsza sciezka
        Vector<Coords> temp;


        vector.add(start); //dodanie do niej startu
        Way.add(vector);  // i wpisanie vektora do listy sciezek

        int k=0;
        while(k<InitMap.maxIterations) {  //szukanie do bolu
            k++;
            temp = minimum(Way,end); //szukanie minimum w kazdej iteracji
            Way.remove(temp);          // usuniecie minimalnej sciezki bo chcemy tylko jej rozwiniecia
            Coords current=temp.lastElement(); //pobranie ostatniego elementu tej sciezki

           for (int i =-1; i<2 ; i++)
           {
               for (int j =-1; j<2 ; j++) {
                   if(j==0 && i==0)continue;
                   if((current.x + i)>=0 && (current.y + j)>=0 && (current.x + i)<=InitMap.x-1 && (current.y + j)<=InitMap.y-1) // jesli nie wychodzimy poza mape
                   {


                       if(InitMap.Mapa[current.x+i][current.y+j]==3) // jesli znalezlismy koniec
                       {
                        vector=temp;  //wpisanie tymczasowego wektoru do naszego zwracanego
                        vector.remove(start); // nie chcemy przykryc punktu startowego przy wyswietlaniu
                           A.result = true;
                        return vector; // ret
                       }
                       else
                       {
                           if(InitMap.Mapa[current.x+i][current.y+j]==0)  // jesli puste pole to rozwijamy w ta strone sciezke
                           {
                            AtomicInteger flag= new AtomicInteger(0); // w wyrazeniach lambda zmienne musza byc atomowe
                               // w naszym rozwiazaniu dziala identycznie jak zwykly int

                            Coords next=new Coords(current.x+i,current.y+j); // nowe pole do wpisania do wektoru
                                                                             // poprzednie koordynaty zwiekszone o +- 1
                               // oba wyrazenia ponizej sluza do tego zeby traktowac mape jako graf

                                temp.forEach(iterator -> {
                                    if(iterator.x==next.x && iterator.y==next.y) {  // nie chcemy stac w miejscu
                                                                                    // raczej sie nie zdarzy ale w razie czego
                                        flag.set(1); // flaga czy mozna rozwinac

                                    }
                                    Way.forEach(list ->{
                                        if(list.lastElement().x==next.x && list.lastElement().y==next.y) //nie chcemy sie cofac
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
                                        newVector.add(next);    // jesli wszystko okej to wpisanie do vektora nowgo pola
                                        Way.add(newVector);     // i dodanie nowego vektora do listy
                                    }

                           }

                       }
                   }

               }
           }

        }
        if (k==InitMap.maxIterations){
	    System.out.println("Nie mozna znalezc sciezki w podanej liczbie iteracji"); // jesli nie zdazylo znalezc to o tym powiadom
            vector.removeAllElements();                      // wyczysczenie tego vektora

        }
        return vector; // zwrocenie pustego vektora (skoro nie bylo rozwiazania nie bedziemy go rysowac)
    }
}
