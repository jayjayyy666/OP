public class Dodo extends Dinosaur {
    public Dodo() {
        super("Dodo", 30, 2, 1, false);
    }

    @Override
    public String getSchopnost() {
        return "Neskodny a pomaly ptak. Idealni pro uplne zacatecniky.";
    }

    @Override
    public String getAsciiArt() {
        return "     __\n" +
               "  __( o)>\n" +
               "  \\ < _)\n" +
               "   )_/\n";
    }
}
