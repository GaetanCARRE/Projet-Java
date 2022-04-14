public class Component {
    private int id;
    protected String name;
    private static int cpt = 0;

    public Component(String name) {
        this.name = name;
        id = cpt++;
    }

    public String toString()
    {
        return "Composant : " +id+ " nommé "+ name;
    }

    public String getName()
    {
        return name;
    }
    public int getId()
    {
        return id;
    }
}
