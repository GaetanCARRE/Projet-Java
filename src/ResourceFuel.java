import java.util.ArrayList;

public class ResourceFuel extends Resource implements IsFuel{
    
    private String category;
    private int value;
    //private ArrayList<Building> tabE;

    public ResourceFuel(String id, String name, /*Extractor e,*/ String c, int v) throws CategoryException
    {
        super(id,name/*,e*/);
        category = c;
        value = v;
        if(!testCategory())
            throw new CategoryException("la categorie ne correspond pas à celle d'un carburant.");
    }
    public boolean testCategory()
    {
        if(category.equals("chemical") || category.equals("nuclear") || category.equals("antimatter"))
        {
            return true;                
        }
        return false;
    }

    public String toString()
    {
        String s = "\n Resource minedby :\n";
        for(Building b:tabE)
            s += b;
        return super.toString() + ", categorie " +category + ", valeur " +value + s;
    }

    public int getValue()
    {
        return value;
    }

    public String getCategory()
    {
        return category;
    }
    /*public void addE(Building E)
    {
       // if((E instanceof ExtractorSE)||(E instanceof Building))
            tabE.add(E);
    }
    public ArrayList<Building> getE()
    {
        return tabE;
    }*/
}
