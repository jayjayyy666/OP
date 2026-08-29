// Hvezda teto hry - vzacny a mocny obojzivelny predator.
// Ochoceni Spinosaura je hlavnim cilem (vyhrou) cele hry.
public class Spinosaurus extends Dinosaur {
    public Spinosaurus() {
        super("Spinosaurus", 450, 40, 8, true);
    }

    @Override
    public String getSchopnost() {
        return "Obrovsky obojzivelny predator s plachtou na zadech. " +
               "Lovi ryby ve vode i korist na sousi. Legenda ostrova ARK!";
    }

    @Override
    public String getAsciiArt() {
        return "         /\\_/\\/\\_/\\\n" +
               "        /         \\\n" +
               "  ___  /  o     o  \\\n" +
               " /   \\/     __      \\\n" +
               "|    (_____/  \\_______)\n" +
               " \\____________________/\n" +
               "   //              \\\\\n" +
               "  //                \\\\\n";
    }
}
