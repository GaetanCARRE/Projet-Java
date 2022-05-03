import java.util.ArrayList;

public class Recette {
    
    String id;
    String name;
    ArrayList<Component> tab_in;
    ArrayList<Integer> qt_in;
    ArrayList<Component> tab_out;
    ArrayList<Integer> qt_out;
    Building producers;
    double time;
    
    public Recette(String id, String name,Building prod, double t)
    {
        this.name=name;
        this.producers=prod;
        this.time = t;
        tab_in = new ArrayList<Component>();
        qt_in = new ArrayList<Integer>();
        tab_out = new ArrayList<Component>();
        qt_out = new ArrayList<Integer>();
    }
    public void setProd(Building b)
    {
        this.producers = b;
    }
    public void add_Component_in(Component R,int qt)
    {
        tab_in.add(R);
        qt_in.add(qt);
    
    }

    public void add_Component_out(Component R,int qt)
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

}
