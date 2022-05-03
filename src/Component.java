import java.util.Comparator;

public class Component {
    private String id;
    protected String name;
    /*private static int cpt = 0;*/

    public Component(String id,String name) {
        this.name = name;
        this.id = id;
        /*id = cpt++;*/
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
