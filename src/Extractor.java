public class Extractor extends ClassicFactory {

    ArrayList<Resource> tabr;
    

    /*public Extractor(Resource r, int u, int d, int s, String name) {*/
    public Extractor(String id, String name, int u, int d, int s)
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

}
