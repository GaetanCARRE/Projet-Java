public class ClassicFactory extends Building {
    protected double speed;
    protected double usage;
    protected double drain;

    public ClassicFactory(String id, String name, double u, double d, double s) throws ConsommationException{
        super(id,name);
        if(d > u)
            throw new ConsommationException("L'usine consomme plus au repos qu'au travail!");
        usage = u;
        drain = d;
        speed = s;

    }

    public ClassicFactory(String id,String name,double u, double d) throws ConsommationException
    {
        super(id,name);
        if(d > u)
            throw new ConsommationException("L'usine consomme plus au repos qu'au travail!");
        usage = u;
        drain = d;
        speed = 1;
    }

    public String toString() {
        return "Usine Classic  : " + name + ", usage : " +usage + ", drain : " +drain + ", speed : "+speed;
    }
}
