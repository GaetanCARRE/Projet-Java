public class Component {
    private String id;
    private String name;
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
}
