// Abstraktni zakladni trida pro vsechny dinosaury ve hre
public abstract class Dinosaur {
    protected final String jmeno;
    protected final int maxZivoty;
    protected int zivoty;
    protected final int utok;
    protected final int obtiznostOchoceni; // 1 (snadne) az 10 (extremne tezke)
    protected final boolean agresivni;
    protected boolean ochoceny = false;

    public Dinosaur(String jmeno, int maxZivoty, int utok, int obtiznostOchoceni, boolean agresivni) {
        this.jmeno = jmeno;
        this.maxZivoty = maxZivoty;
        this.zivoty = maxZivoty;
        this.utok = utok;
        this.obtiznostOchoceni = obtiznostOchoceni;
        this.agresivni = agresivni;
    }

    public abstract String getSchopnost();
    public abstract String getAsciiArt();

    public String getJmeno() { return jmeno; }
    public int getZivoty() { return zivoty; }
    public int getMaxZivoty() { return maxZivoty; }
    public int getUtok() { return utok; }
    public int getObtiznostOchoceni() { return obtiznostOchoceni; }
    public boolean isAgresivni() { return agresivni; }
    public boolean isOchoceny() { return ochoceny; }
    public void setOchoceny(boolean hodnota) { this.ochoceny = hodnota; }

    public void utrpiZraneni(int mnozstvi) {
        zivoty -= mnozstvi;
        if (zivoty < 0) zivoty = 0;
    }

    public boolean jeNazivu() {
        return zivoty > 0;
    }
}
