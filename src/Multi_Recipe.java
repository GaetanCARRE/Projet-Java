import java.util.ArrayList;

public class Multi_Recipe extends Recette{
    
    ArrayList<Resource> tab_out;
    ArrayList<int> qt_out;
    
    
    public Recette(String name,Building prod, int t)
    {
        super(name, prod, t);
        tab_out = new ArrayList<Resource>();
        qt_out = new ArrayList<int>();
    }
    public void add_Resource(Resource R,int qt)
    {
        tab_out.add(R);
        qt_out.add(qt);
    
    }
    public String toString()
    {
        String s= super.toString() + "Pour Produire :\n";
        for(int i=0;i<tab_out.size();i++)
        {
            s += "- "+tab_in.get(i)+ " "+qt_in.get(i)+"\n"  
        
        }
        return s+ " toutes les "+time+" secondes.";
    }

}
