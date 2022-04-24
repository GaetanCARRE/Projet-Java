public class Resource extends Component {

    private Extractor extractor;

    public Resource(String id, String name, Extractor extractor)
    {
        super(id,name);
        this.extractor = extractor;
    }

    public String toString()
    {
        return "Nom : "+super.getName()+ " " + extractor;
    }
}
