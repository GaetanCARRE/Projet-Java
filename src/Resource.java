import java.util.ArrayList;
public class Resource extends Component {

    private ArrayList<Building> tabE; /*choix d'utiliser l'ArrayList pour pouvoir réaliser les liens de notre diagramme de classe apres creation des objets*/

    public Resource(String id, String name)
    {
        super(id,name);
        tabE = new ArrayList<Building>();
    }
    public void addE(Building E)
    {
        if((E instanceof ExtractorSE)||(E instanceof Exctractor))
            tabE.add(E);
    }

    public String toString()
    {
        return "Nom : "+super.getName()+ " " + tabE;
    }
}
