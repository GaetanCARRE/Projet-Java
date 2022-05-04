import java.util.ArrayList;
import java.util.Comparator;


public class Recette {
    
    String id;
    String name;
    ArrayList<Component> tab_in;
    ArrayList<Double> qt_in;
    ArrayList<Component> tab_out;
    ArrayList<Double> qt_out;
    Building producers;
    double time;
    
    public Recette(String id, String name,Building prod, double t)
    {
        this.name=name;
        this.producers=prod;
        this.time = t;
        tab_in = new ArrayList<Component>();
        qt_in = new ArrayList<Double>();
        tab_out = new ArrayList<Component>();
        qt_out = new ArrayList<Double>();
    }
    public void setProd(Building b)
    {
        this.producers = b;
    }
    public void add_Component_in(Component R, double qt)
    {
        tab_in.add(R);
        qt_in.add(qt);
    
    }

    public void add_Component_out(Component R,double qt)
    {
        tab_out.add(R);
        qt_out.add(qt);
    
    }
    public String toString()
    {
        String s= "La recette in "+name+" nécessite : \n";
        for(int i=0;i<tab_in.size();i++)
        {
            s += "- "+tab_in.get(i)+ " "+qt_in.get(i)+"\n";
        
        }
        s += "La recette out "+name+" nécessite : \n";
        for(int i=0;i<tab_out.size();i++)
        {
            s += "- "+tab_out.get(i)+ " "+qt_out.get(i)+"\n";
        
        }
        return s;
    }
    public double getTime()
    {
        return time;
    }
    public String getName()
    {
        return name;
    }
    /*
     * Comparator pour le tri des composant par nom en s'inspirant de : http://www.codeurjava.com/2015/10/trier-un-arraylist-dobjets-avec-comparable-et-comparator.html
     */
    public static Comparator<Recette> ComparatorName = new Comparator<Recette>() {
      
        @Override
        public int compare(Recette r1, Recette r2) {
            return r1.getName().compareTo(r2.getName());
        }
    };

}
