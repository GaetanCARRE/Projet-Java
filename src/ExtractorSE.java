public class ExtractorSE extends Building {
    private int speed;

    public ExtractorSE(int s, String name) {
        super(name);
        speed = s;

    }

    public String toString() {
        return super.toString() + ", vitesse " + speed;
    }

}
