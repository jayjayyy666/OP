public class Velociraptor extends Dinosaur {
    public Velociraptor() {
        super("Velociraptor", 80, 12, 4, true);
    }

    @Override
    public String getSchopnost() {
        return "Rychly a mrstny lovec, ve volne prirode utoci ve smeckach.";
    }

    @Override
    public String getAsciiArt() {
        return "    ^,,,^\n" +
               "   ( o.o )\n" +
               "    >\\_/<\n";
    }
}
