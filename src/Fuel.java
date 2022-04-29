
public class Fuel extends Component  implements IsFuel {   
    private String category;
    private int value;
    //ArrayList<Extractor> tabE; /*ce sont les resourcefuel qui ont des extracteur(s) en données*/

    public Fuel(String id, String name, int v, String c) throws CategoryException
    {
        super(id,name);
        category =c;
        value =v;
        //tabE = new ArrayList<Extractor>();
        if( ! testCategory())
            throw new CategoryException("La categorie ne correspond à celle d'un carburant.");

    }
    /*public void addE(Extractor E)
    {
        tabE.add(E);
    }*/
    public boolean testCategory()
    {
        if(category.equals("chemical") || category.equals("nuclear") || category.equals("antimatter"))
        {
            return true;                
        }
        return false;
    }

    public String toString()
    {
        /*String s="\n";
        for (Extractor e : tabE) {
            s+= e;
            s+= "\n";
        }*/
        return  "Nom : "+ name+ ", Categorie " + category + ", value" +value + "Liste extracteur : "/*+s*/ ;
    }

    public int getValue()
    {
        return value;
    } 
    public String getCategory()
    {
        return category;
    }
    
}
