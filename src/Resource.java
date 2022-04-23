public class Resource extends Component {

    private Extractor extractor;

    public Resource(String name, Extractor extractor)
    {
        super(name);
        this.extractor = extractor;
    }

    public String toString()
    {
        return "Nom : "+super.getName()+ " " + extractor;
    }
}
