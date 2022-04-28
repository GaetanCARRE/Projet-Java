import java.util.ArrayList;
public class ExtractorSE extends Building {
    private int speed;
    private ArrayList<Resource> tabr;

    public ExtractorSE(String id, String name, int s) {
        super(id,name);
        speed = s;
        /*this.r=r;*/
        tabr = new ArrayList<Resource>();

    }
    public void addr(Resource r)
    {
        if(tabr.size()<1)
            tabr.add(r);
           
    }

    public String toString() {
        return super.toString() +",extrait la ressource "+/*r+*/ " à la vitesse " + speed ;
    }

}
