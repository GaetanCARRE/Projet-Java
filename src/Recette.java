import java.util.ArrayList;

public abstract class Recette {
    
    String name;
    ArrayList<Resource> tab_in;
    ArrayList<Integer> qt_in;
    ArrayList<Resource> tab_out;
    ArrayList<Integer> qt_out;
    Building producers;
    int time;
    
    public Recette(String name,Building prod, int t)
    {
        this.name=name;
        this.producers=prod;
        this.time = t;
        tab_in = new ArrayList<Resource>();
        qt_in = new ArrayList<Integer>();
        tab_out = new ArrayList<Resource>();
        qt_out = new ArrayList<Integer>();
    }
    public void add_Resource_in(Resource R,int qt)
    {
        tab_in.add(R);
        qt_in.add(qt);
    
    }

    public void add_Resource_out(Resource R,int qt)
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

}
