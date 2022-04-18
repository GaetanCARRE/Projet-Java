import java.util.ArrayList;

public abstract class Recette {
    ArrayList<Resource> tab_in;
    ArrayList<int> qt_in;
    Building building;
    int time;
    //il faut peut etre ajouter un nom 
    
    public Recette(Building prod,int t)
    {
        tab_in = new ArrayList<Resource>();
        qt_in = new ArrayList<int>();
        building = prod;
        time = t;

    }
    public add_Resource_in(Resource r, int qt)
    {
        // ajouter une exception qui verifie que la quantite associer a la ressource est positive
        tab_in.add(r);
        qt_in(qt);
    
    }
    public String toString()
    {
        String s = "La recette nécessite les éléments suivants :\n";
        for(int i=0; i < tab_in.size();i++)
        {
            s += "- " tab_in.get(i)+ " "+ qt_in.get(i);
        }  
        return s;
    }
}
