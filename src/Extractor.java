public class Extractor extends ClassicFactory {

    Resource resource;
    

    public Extractor(Resource r, int u, int d, int s, String name) {
        super(u,d,s,name);
        resource =r;

    }

    public Extractor(Resource r, int u, int d, String name) {
        super(u,d,name);
        resource =r;

    }

    public String toString() {
        return "Extracteur : " + name +  ", usage : " +usage + ", drain : " +drain + ", speed : "+speed;
    }

}
