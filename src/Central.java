public class Central extends CentralRE {

    private IsFuel fuel;
    private String category;
    public Central(String id, String name,String t,int v, String c) throws CategoryException
    {
        super(id,name,t,v);
        this.fuel = null; /*on part du principe que l'on ne connait pas le carburant à la construction de la centrale*/
        this.category = c;
        if(!testCategory())
            throw new CategoryException("la categorie de la centrale ne correspond pas à celle d'un carburant.");
        
    }
    public void SetFuel(IsFuel f) throws DiffCategoryFCException
    {
        this.fuel = f;
        if (!fuel.getCategory().equals(category))
            throw new DiffCategoryFCException("La categorie de la centrale ne correspond pas à celle du carburant.");
        
    
    }

     public boolean testCategory()
    {
        if(category.equals("chemical") || category.equals("nuclear") || category.equals("antimatter"))
        {
            return true;                
        }
        return false;
    }
    public int conso()
    {
        return fuel.getValue()/value;
    }
    public String toString()
    {
        return super.toString() +"category : " + category + " Fuel: "+fuel;
    }
}
