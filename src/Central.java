public class Central extends CentralRE {

    private IsFuel fuel;
    private String category;
    public Central(IsFuel fuel,String name,String t,int v)
    {
        super(name,t,v);
        this.fuel = fuel;
        this.category = fuel.getCategory();
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