public class ResourceFuel extends Resource implements IsFuel{
    
    private String category;
    private int value;

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
        return super.toString() + ", categorie " +category + ", valeur " +value;
    }

    public int getValue()
    {
        return value;
    }

    public String getCategory()
    {
        return category;
    }
}
