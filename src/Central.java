public class Central extends CentralRE {

    private IsFuel fuel;
    private String category;
    public Central(IsFuel fuel,String name,String t,int v, String c) throws DiffCategoryException
    {
        super(name,t,v);
        this.fuel = fuel;
        this.category = c;
        if (!fuel.getCategory().equals(c))
            throw DiffCategoryException("La categorie de la centrale ne correspond pas à celle du carburant");
        
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
