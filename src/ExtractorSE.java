public class ExtractorSE extends Building {
    private int speed;
    private Resource r;

    public ExtractorSE(int s, Resource r, String name) {
        super(name);
        speed = s;
        this.r=r;

    }

    public String toString() {
        return super.toString() +",extrait la ressource "+r+ " à la vitesse " + speed ;
    }

}
