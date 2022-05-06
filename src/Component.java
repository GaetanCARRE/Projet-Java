import java.util.Comparator;
import java.util.ArrayList;

public class Component {
    private String id;
    protected String name;
    /*private static int cpt = 0;*/
    public ArrayList <Recette> trec; // le mettre en private nécessiterai de créer une fonction pour ajouter les recette et les un geteur.

    public Component(String id,String name) {
        this.name = name;
        this.id = id;
        /*id = cpt++;*/
        trec = new ArrayList<Recette>();
    }

    public String toString()
    {
        return "Composant : " +id+ " nommé "+ name;
    }

    public String getName()
    {
        return name;
    }
    public String getId()
    {
        return id;
    }
    /*
     * Comparator pour le tri des composant par nom en s'inspirant de : http://www.codeurjava.com/2015/10/trier-un-arraylist-dobjets-avec-comparable-et-comparator.html
     */
    public static Comparator<Component> ComparatorName = new Comparator<Component>() {
      
        @Override
        public int compare(Component c1, Component c2) {
            return c1.getName().compareTo(c2.getName());
        }
    };
}
