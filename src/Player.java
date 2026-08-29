// Trida reprezentujici hrace a jeho stav (zdravi, hlad, zizen, stamina...)
public class Player {
    private final String jmeno;
    private int zdravi = 100;
    private final int maxZdravi = 100;
    private int hlad = 100;
    private int zizen = 100;
    private int stamina = 100;
    private int den = 1;
    private int akciDnes = 0;
    private final Inventory inventory = new Inventory();
    private Dinosaur ochoceny = null;

    public Player(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getJmeno() { return jmeno; }
    public int getZdravi() { return zdravi; }
    public int getHlad() { return hlad; }
    public int getZizen() { return zizen; }
    public int getStamina() { return stamina; }
    public int getDen() { return den; }
    public Inventory getInventory() { return inventory; }
    public Dinosaur getOchoceny() { return ochoceny; }
    public void setOchoceny(Dinosaur d) { this.ochoceny = d; }

    public boolean jeNazivu() {
        return zdravi > 0;
    }

    public void utrpiZraneni(int mnozstvi) {
        zdravi -= mnozstvi;
        if (zdravi < 0) zdravi = 0;
    }

    public void uzdrav(int mnozstvi) {
        zdravi = Math.min(maxZdravi, zdravi + mnozstvi);
    }

    public void pouzijStaminu(int mnozstvi) {
        stamina = Math.max(0, stamina - mnozstvi);
    }

    public void obnovStaminu(int mnozstvi) {
        stamina = Math.min(100, stamina + mnozstvi);
    }

    public void jez(int mnozstvi) {
        hlad = Math.min(100, hlad + mnozstvi);
    }

    public void pij(int mnozstvi) {
        zizen = Math.min(100, zizen + mnozstvi);
    }

    // Zavola se po kazde akci - snizi hlad/zizen a resi plynuti dni
    public void uplynulKrok() {
        hlad = Math.max(0, hlad - 4);
        zizen = Math.max(0, zizen - 6);
        if (hlad == 0) {
            System.out.println("Umiras hlady! Ztracis zdravi.");
            utrpiZraneni(3);
        }
        if (zizen == 0) {
            System.out.println("Umiras zizni! Ztracis zdravi.");
            utrpiZraneni(4);
        }
        akciDnes++;
        if (akciDnes >= 6) {
            akciDnes = 0;
            den++;
            System.out.println("\n*** Nastal den " + den + "! ***");
        }
    }

    public void vypisStav() {
        System.out.println("\n=== STAV HRACE (Den " + den + ") ===");
        System.out.println("Zdravi:  " + zdravi + "/100");
        System.out.println("Hlad:    " + hlad + "/100");
        System.out.println("Zizen:   " + zizen + "/100");
        System.out.println("Stamina: " + stamina + "/100");
        if (ochoceny != null) {
            System.out.println("Ochoceny spolecnik: " + ochoceny.getJmeno());
        }
        System.out.println("===========================");
    }
}
