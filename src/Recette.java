import java.util.ArrayList;

public abstract class Recette {
    
    String name;
    ArrayList<Resource> tab_in;
    ArrayList<int> qt_in;
    Building producers;
    
    public Recette(String name,Building prod)
    {
        this.name=name;
        this.producers=prod;
        tab_in = new ArrayList<Resource>();
        qt_in = new ArrayList<int>();
    }
    public void add_Resource(Resource R,int qt)
    {
        tab_in.add(R);
        qt_in.add(qt);
    
    }
    public String toString()
    {
        String s= "La recette "+name+" nécessite : \n";
        for(int i=0;i<tab_in.size();i++)
        {
            s += "- "+tab_in.get(i)+ " "+qt_in.get(i)+"\n"  
        
        }
    }

}
