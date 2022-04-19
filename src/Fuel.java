import java.util.ArrayList;

public class Fuel extends Component  implements IsFuel {   
    private String category;
    private int value;
    ArrayList<Extractor> tabE;

    public Fuel(String name, int v, String c) throws CategoryException
    {
        super(name);
        category =c;
        value =v;
        if( ! testCategory())
            throw new CategoryException("La categorie ne correspond à celle d'un carburant.");

    }
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
        String s="\n";
        for (Extractor e : tabE) {
            s+= e;
            s+= "\n";
        }
        return  "Nom : "+ name+ ", Categorie " + category + ", value" +value + "Liste extracteur : "+s ;
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
