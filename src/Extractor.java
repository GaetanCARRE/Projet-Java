import java.util.ArrayList;
public class Extractor extends ClassicFactory implements Extracteur{

    ArrayList<Resource> tabr; //une Variable Resource set a null dans le constructeurs serait plus adaptés a notre situation mais on a initialement penser qu'un extractor pouvait extraire différente ressource.
    

    /*public Extractor(Resource r, int u, int d, int s, String name) {*/
    public Extractor(String id, String name, int u, int d, double s) throws ConsommationException
    {
        super(id,name,u,d,s);
        tabr = new ArrayList<Resource>();

    }

    /*public Extractor(Resource r, int u, int d, String name) {*/
    public Extractor(String id, String name, int u, int d)
    {
        super(id,name,u,d);
        tabr = new ArrayList<Resource>();
    }
    
    public void addR(Resource R)
    {
        if(tabr.size()<1)
            tabr.add(R);
    }

    public String toString() {
        return "Extracteur : " + name +  ", usage : " +usage + ", drain : " +drain + ", speed : "+speed;
    }
    public Resource getResource()
    {
        if (tabr.size()>0)
            return tabr.get(0);
        else 
            return null;
    }

}
