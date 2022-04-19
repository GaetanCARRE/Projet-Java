public class ResourceFuel extends Resource implements IsFuel{
    
    private String category;
    private int value;

    public ResourceFuel(String name, Extractor e, String c, int v)
    {
        super(name,e);
        category = c;
        value = v;
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
