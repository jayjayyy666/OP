public class Triceratops extends Dinosaur {
    public Triceratops() {
        super("Triceratops", 200, 18, 3, false);
    }

    @Override
    public String getSchopnost() {
        return "Mohutny rostlinozravec s pevnym krunyrem a tremi rohy.";
    }

    @Override
    public String getAsciiArt() {
        return "  _____\n" +
               " /     \\_,--.\n" +
               "(  o   o )   )\n" +
               " \\___/\\_/---'\n";
    }
}
