// Vyctovy typ pro vsechny suroviny a predmety, ktere hrac muze mit v inventari
public enum ItemType {
    WOOD("Drevo"),
    STONE("Kamen"),
    FIBER("Vlakno"),
    BERRY("Bobule"),
    RAW_MEAT("Syrove maso"),
    COOKED_MEAT("Varene maso"),
    NARCOTIC("Narkotikum"),
    WATER("Voda");

    private final String popis;

    ItemType(String popis) {
        this.popis = popis;
    }

    public String getPopis() {
        return popis;
    }
}
