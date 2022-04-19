public class Central extends CentralRE {

    private IsFuel fuel;
    private String category;
    public Central(IsFuel fuel,String name,String t,int v, String c) throws DiffCategoryException, CategoryException
    {
        super(name,t,v);
        this.fuel = fuel;
        this.category = c;
        if(!testCategory())
            throw new CategoryException("la categorie de la centrale ne correspond pas à celle d'un carburant.");
        if (!fuel.getCategory().equals(c))
            throw new DiffCategoryException("La categorie de la centrale ne correspond pas à celle du carburant.");
        
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
        fuel.getValue()/value;
    }
    public String toString()
    {
        return super.toString() +"category : " + category + " Fuel: "+fuel;
    }
}
