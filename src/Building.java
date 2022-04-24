public class Building extends Component {
    

    public Building(String id, String name)
    {
        super(id,name);

    }

    public String toString()
    {
        return "Batiment ID : " + super.getId()+", nom : "+super.getNom();
    }
}
