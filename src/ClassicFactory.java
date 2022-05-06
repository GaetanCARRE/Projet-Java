import java.util.ArrayList;
public class ClassicFactory extends Building {
    protected double speed;
    protected double usage;
    protected double drain;
    protected ArrayList<Resource> tabr;

    public ClassicFactory(String id, String name, double u, double d, double s) throws ConsommationException{
        super(id,name);
        if(d > (u+0.01))
            throw new ConsommationException("L'usine consomme plus au repos qu'au travail!");
        usage = u;
        drain = d;
        speed = s;
        tabr = new ArrayList<Resource>();

    }

    public ClassicFactory(String id,String name,double u, double d) throws ConsommationException
    {
        super(id,name);
        if(d > (u+0.01))
            throw new ConsommationException("L'usine consomme plus au repos qu'au travail!");
        usage = u;
        drain = d;
        speed = 1;
    }
    public void addR(Resource R)
    {
            tabr.add(R);
    }
    public ArrayList<Resource> getResource()
    {
            return tabr;
    }

    public String toString() {
        String s = "\nCe bâtiment peut avoir pour recette :\n";
        for(Recette r:this.trec)
            s+= r+"\n";
        return "Usine Classic  : " + name + ", usage : " +usage + ", drain : " +drain + ", speed : "+speed + s;
        
    }
}
