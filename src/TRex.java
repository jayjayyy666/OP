public class TRex extends Dinosaur {
    public TRex() {
        super("Tyranosaurus Rex", 400, 35, 9, true);
    }

    @Override
    public String getSchopnost() {
        return "Kral predatoru ostrova. Extremne nebezpecny a temer neochocitelny.";
    }

    @Override
    public String getAsciiArt() {
        return "       ___\n" +
               "     >(o o)__\n" +
               "      (_(___)\n" +
               "       //  \\\\\n";
    }
}
