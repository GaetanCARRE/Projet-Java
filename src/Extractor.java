public class Extractor extends ClassicFactory implements Extracteur{

    //ArrayList<Resource> tabr; une Variable Resource set a null dans le constructeurs serait plus adaptés a notre situation mais on a initialement penser qu'un extractor pouvait extraire différente ressource.
    

    /*public Extractor(Resource r, int u, int d, int s, String name) {*/
    public Extractor(String id, String name, int u, int d, double s) throws ConsommationException
    {
        super(id,name,u,d,s);
        //tabr = new ArrayList<Resource>();

    }

    /*public Extractor(Resource r, int u, int d, String name) {*/
    public Extractor(String id, String name, int u, int d) throws ConsommationException
    {
        super(id,name,u,d);
        //tabr = new ArrayList<Resource>();
    }
    
    /*public void addR(Resource R)
    {
            tabr.add(R);
    }*/

    public String toString() {
        String s = "\nCe bâtiment peut avoir pour recette :\n";
        for(Recette r:this.trec)
            s+= r+"\n";
        return "Extracteur : " + name +  ", usage : " +usage + ", drain : " +drain + ", speed : "+speed + s;
    }
    /*public ArrayList<Resource> getResource()
    {
        //if (tabr.size()>0)
            return tabr;
        //else 
        //    return null;
    }*/

}
