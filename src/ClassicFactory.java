public class ClassicFactory extends Building {
    int speed;
    int usage;
    int drain;

    public ClassicFactory(String id, String name, int u, int d, int s) throws ConsommationException{
        super(id,name);
        if(drain > usage)
            throw new ConsommationException("L'usine consomme plus au repos qu'au travail!");
        usage = u;
        drain = d;
        speed = s;

    }

    public ClassicFactory(String id,String name, int u, int d) {
        super(id,name);
        usage = u;
        drain = d;
        speed = 1;
    }

    public String toString() {
        return "Usine Classic  : " + name + ", usage : " +usage + ", drain : " +drain + ", speed : "+speed;
    }
}
