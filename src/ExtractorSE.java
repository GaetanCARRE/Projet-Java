import java.util.ArrayList;
public class ExtractorSE extends Building implements Extracteur {
    private int speed;
    private ArrayList<Resource> tabr; // une variable Resource set à null aurait été plus adaptés à notre code mais on a initialement penser qu'un extractor pouvait extraire différentes ressources.

    public ExtractorSE(String id, String name, int s) {
        super(id,name);
        speed = s;
        /*this.r=r;*/
        tabr = new ArrayList<Resource>();

    }
    public void addr(Resource r)
    {
        /*if(tabr.size()<1)*/
            tabr.add(r);
           
    }

    public String toString() {
        /*String s = "\nCe bâtiment peut avoir pour recette :\n";
        for(Recette r:super.trec)
            s+= r+"\n";*/
        return super.toString() +",extrait la ressource "+/*r+*/ " à la vitesse " + speed /*+ s*/;
    }
    public ArrayList<Resource> getResource() 
    {
        //if(tabr.size()>0)
            return tabr;
        //else 
        //    return null;
    }

}
