import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

// Hlavni trida, ktera ridi celou hru - menu, nahodne udalosti, souboje a ochocovani
public class GameEngine {
    private Player hrac;
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private boolean bezi = true;
    private boolean vyhra = false;

    public void spustit() {
        vypisBanner();
        try {
            System.out.print("Jak se jmenuje tva postava? ");
            String jmeno = scanner.nextLine().trim();
            if (jmeno.isEmpty()) jmeno = "Trosecnik";
            hrac = new Player(jmeno);

            System.out.println("\nVitej, " + jmeno + "! Tva lod ztroskotala u pobrezi tajemneho ostrova");
            System.out.println("plneho divokych dinosauru. Musis prezit - shanet jidlo, vodu");
            System.out.println("a postavit se nebezpecnym tvorum.");
            System.out.println("\nLegenda vypravi o mocnem SPINOSAUROVI, ktery vladne bazinam ostrova.");
            System.out.println("Dokazes ho jednou ochocit?\n");

            hrac.getInventory().add(ItemType.BERRY, 3);
            hrac.getInventory().add(ItemType.WATER, 2);

            while (bezi && hrac.jeNazivu() && !vyhra) {
                hrac.vypisStav();
                vypisMenu();
                String volba = scanner.nextLine().trim();
                zpracujVolbu(volba);
            }

            if (vyhra) {
                vypisVitezstvi();
            } else if (!hrac.jeNazivu()) {
                vypisProhru();
            } else {
                System.out.println("\nDiky za hrani! Nashledanou na ostrove ARK.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("\n(Vstup byl neocekavane ukoncen. Hra konci.)");
        }
    }

    private void vypisBanner() {
        System.out.println("=================================================");
        System.out.println("         OSTROV ARK - jednoducha java hra");
        System.out.println("=================================================");
    }

    private void vypisMenu() {
        System.out.println("\nCo chces delat?");
        System.out.println("1. Prozkoumat okoli");
        System.out.println("2. Sbirat suroviny");
        System.out.println("3. Uvarit maso (1x drevo + 1x syrove maso)");
        System.out.println("4. Vyrobit narkotikum (2x bobule + 1x vlakno)");
        System.out.println("5. Jist / Pit");
        System.out.println("6. Odpocivat");
        System.out.println("7. Zobrazit inventar");
        System.out.println("8. Ukoncit hru");
        System.out.print("> ");
    }

    private void zpracujVolbu(String volba) {
        switch (volba) {
            case "1": prozkoumat(); break;
            case "2": sbirat(); break;
            case "3": uvaritMaso(); break;
            case "4": vyrobitNarkotikum(); break;
            case "5": jistPit(); break;
            case "6": odpocivat(); break;
            case "7": hrac.getInventory().print(); break;
            case "8": bezi = false; break;
            default: System.out.println("Neplatna volba, zkus to znovu.");
        }
    }

    private void prozkoumat() {
        if (hrac.getStamina() < 10) {
            System.out.println("Jsi prilis unaveny na prozkoumavani. Musis odpocivat.");
            return;
        }
        hrac.pouzijStaminu(10);
        hrac.uplynulKrok();
        if (!hrac.jeNazivu()) return;

        int hod = random.nextInt(100);
        if (hod < 35) {
            int drevo = 1 + random.nextInt(3);
            int kamen = random.nextInt(2);
            int vlakno = random.nextInt(2);
            int voda = random.nextInt(2);
            hrac.getInventory().add(ItemType.WOOD, drevo);
            hrac.getInventory().add(ItemType.STONE, kamen);
            hrac.getInventory().add(ItemType.FIBER, vlakno);
            hrac.getInventory().add(ItemType.WATER, voda);
            StringBuilder sb = new StringBuilder("Nasel jsi: " + drevo + "x drevo");
            if (kamen > 0) sb.append(", ").append(kamen).append("x kamen");
            if (vlakno > 0) sb.append(", ").append(vlakno).append("x vlakno");
            if (voda > 0) sb.append(", ").append(voda).append("x voda");
            System.out.println(sb + ".");
        } else if (hod < 55) {
            System.out.println("Rozhlizis se kolem, ale nic zajimaveho jsi nenasel.");
        } else {
            Dinosaur d = vytvorNahodnehoDinosaura();
            setkani(d);
        }
    }

    private void sbirat() {
        if (hrac.getStamina() < 8) {
            System.out.println("Jsi prilis unaveny na sbirani. Musis odpocivat.");
            return;
        }
        hrac.pouzijStaminu(8);
        hrac.uplynulKrok();
        if (!hrac.jeNazivu()) return;

        int drevo = 1 + random.nextInt(4);
        int vlakno = 1 + random.nextInt(3);
        int bobule = random.nextInt(3);
        hrac.getInventory().add(ItemType.WOOD, drevo);
        hrac.getInventory().add(ItemType.FIBER, vlakno);
        hrac.getInventory().add(ItemType.BERRY, bobule);
        StringBuilder sb = new StringBuilder("Sebral jsi " + drevo + "x drevo, " + vlakno + "x vlakno");
        if (bobule > 0) sb.append(", ").append(bobule).append("x bobule");
        System.out.println(sb + ".");
    }

    private Dinosaur vytvorNahodnehoDinosaura() {
        int hod = random.nextInt(100);
        if (hod < 40) return new Dodo();
        else if (hod < 65) return new Velociraptor();
        else if (hod < 85) return new Triceratops();
        else if (hod < 95) return new TRex();
        else return new Spinosaurus();
    }

    private void setkani(Dinosaur d) {
        System.out.println("\nNarazil jsi na divokeho dinosaura: " + d.getJmeno() + "!");
        System.out.println(d.getAsciiArt());
        System.out.println(d.getSchopnost());
        if (d instanceof Spinosaurus) {
            System.out.println(">>> Toto je vzacny a legendarni SPINOSAURUS! <<<");
        }

        System.out.println("Co udelas?");
        System.out.println("1. Zautocit");
        System.out.println("2. Zkusit ochocit (potrebujes narkotikum a jidlo)");
        System.out.println("3. Utect");
        System.out.print("> ");
        String volba = scanner.nextLine().trim();

        switch (volba) {
            case "1": boj(d); break;
            case "2": zkusOchocit(d); break;
            default: System.out.println("Opatrne couvas a mizis v houstine."); break;
        }
    }

    private void boj(Dinosaur d) {
        System.out.println("Bojujes s " + d.getJmeno() + "!");
        while (d.jeNazivu() && hrac.jeNazivu()) {
            int zranHrac = 8 + random.nextInt(8);
            d.utrpiZraneni(zranHrac);
            System.out.println("Zasahujes " + d.getJmeno() + " za " + zranHrac + " poskozeni. (Zbyva: " + d.getZivoty() + "/" + d.getMaxZivoty() + ")");
            if (!d.jeNazivu()) break;

            int zranDino = Math.max(0, d.getUtok() - random.nextInt(6));
            hrac.utrpiZraneni(zranDino);
            System.out.println(d.getJmeno() + " te zasahuje za " + zranDino + " poskozeni. (Tve zdravi: " + hrac.getZdravi() + "/100)");
        }

        if (!hrac.jeNazivu()) return;

        System.out.println("Porazil jsi " + d.getJmeno() + "!");
        int maso = 2 + random.nextInt(3);
        hrac.getInventory().add(ItemType.RAW_MEAT, maso);
        System.out.println("Ziskal jsi " + maso + "x syrove maso.");
    }

    private void zkusOchocit(Dinosaur d) {
        boolean maJidlo = hrac.getInventory().has(ItemType.RAW_MEAT, 1) || hrac.getInventory().has(ItemType.BERRY, 2);
        if (!hrac.getInventory().has(ItemType.NARCOTIC, 1) || !maJidlo) {
            System.out.println("Nemas dostatek zasob na ochoceni (potrebujes narkotikum a jidlo).");
            System.out.println(d.getJmeno() + " si te vsimne a zautoci!");
            boj(d);
            return;
        }

        hrac.getInventory().remove(ItemType.NARCOTIC, 1);
        if (hrac.getInventory().has(ItemType.RAW_MEAT, 1)) {
            hrac.getInventory().remove(ItemType.RAW_MEAT, 1);
        } else {
            hrac.getInventory().remove(ItemType.BERRY, 2);
        }

        int sance = Math.max(10, 80 - d.getObtiznostOchoceni() * 7);
        int hod = random.nextInt(100);
        System.out.println("Pokousis se ochocit " + d.getJmeno() + "... (sance na uspech: " + sance + "%)");

        if (hod < sance) {
            d.setOchoceny(true);
            hrac.setOchoceny(d);
            System.out.println("\n*** USPECH! Ochocil jsi " + d.getJmeno() + "! ***\n");
            if (d instanceof Spinosaurus) {
                vyhra = true;
                System.out.println("Ochocil jsi legendarniho SPINOSAURA! Jsi ted pravym panem ostrova ARK!");
            }
        } else {
            System.out.println(d.getJmeno() + " se vytrhl a v panice zautocil!");
            boj(d);
        }
    }

    private void uvaritMaso() {
        if (!hrac.getInventory().has(ItemType.RAW_MEAT, 1) || !hrac.getInventory().has(ItemType.WOOD, 1)) {
            System.out.println("Potrebujes alespon 1x syrove maso a 1x drevo na uvareni.");
            return;
        }
        hrac.getInventory().remove(ItemType.RAW_MEAT, 1);
        hrac.getInventory().remove(ItemType.WOOD, 1);
        hrac.getInventory().add(ItemType.COOKED_MEAT, 1);
        System.out.println("Uvaril jsi 1x varene maso.");
    }

    private void vyrobitNarkotikum() {
        if (!hrac.getInventory().has(ItemType.BERRY, 2) || !hrac.getInventory().has(ItemType.FIBER, 1)) {
            System.out.println("Potrebujes 2x bobule a 1x vlakno na vyrobu narkotika.");
            return;
        }
        hrac.getInventory().remove(ItemType.BERRY, 2);
        hrac.getInventory().remove(ItemType.FIBER, 1);
        hrac.getInventory().add(ItemType.NARCOTIC, 1);
        System.out.println("Vyrobil jsi 1x narkotikum.");
    }

    private void jistPit() {
        System.out.println("Co chces udelat?");
        System.out.println("1. Snist bobule (+15 hlad)");
        System.out.println("2. Snist varene maso (+35 hlad, +10 zdravi)");
        System.out.println("3. Snist syrove maso (+20 hlad, riziko otravy)");
        System.out.println("4. Napit se vody (+30 zizen)");
        System.out.println("5. Zrusit");
        System.out.print("> ");
        String volba = scanner.nextLine().trim();
        switch (volba) {
            case "1":
                if (hrac.getInventory().has(ItemType.BERRY, 1)) {
                    hrac.getInventory().remove(ItemType.BERRY, 1);
                    hrac.jez(15);
                    System.out.println("Snedl jsi bobule.");
                } else {
                    System.out.println("Nemas zadne bobule.");
                }
                break;
            case "2":
                if (hrac.getInventory().has(ItemType.COOKED_MEAT, 1)) {
                    hrac.getInventory().remove(ItemType.COOKED_MEAT, 1);
                    hrac.jez(35);
                    hrac.uzdrav(10);
                    System.out.println("Snedl jsi varene maso.");
                } else {
                    System.out.println("Nemas zadne varene maso.");
                }
                break;
            case "3":
                if (hrac.getInventory().has(ItemType.RAW_MEAT, 1)) {
                    hrac.getInventory().remove(ItemType.RAW_MEAT, 1);
                    hrac.jez(20);
                    if (random.nextInt(100) < 25) {
                        System.out.println("Syrove maso ti nesedlo... otrava jidlem!");
                        hrac.utrpiZraneni(10);
                    } else {
                        System.out.println("Snedl jsi syrove maso.");
                    }
                } else {
                    System.out.println("Nemas zadne syrove maso.");
                }
                break;
            case "4":
                if (hrac.getInventory().has(ItemType.WATER, 1)) {
                    hrac.getInventory().remove(ItemType.WATER, 1);
                    hrac.pij(30);
                    System.out.println("Napil jsi se vody.");
                } else {
                    System.out.println("Nemas zadnou vodu.");
                }
                break;
            default:
                System.out.println("Zrusil jsi akci.");
        }
    }

    private void odpocivat() {
        System.out.println("Odpocivas...");
        hrac.obnovStaminu(30);
        hrac.uplynulKrok();
        if (hrac.jeNazivu()) {
            System.out.println("Stamina obnovena.");
        }
    }

    private void vypisVitezstvi() {
        System.out.println("\n*****************************************************");
        System.out.println("*     GRATULUJEME! Ochocil jsi SPINOSAURA!         *");
        System.out.println("*     Jsi ted nepopiratelnym panem ostrova ARK.    *");
        System.out.println("*****************************************************");
    }

    private void vypisProhru() {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("Tva postava zemrela na ostrove...");
        System.out.println("Konec hry. Zkus to znovu a tentokrat prezij dost dlouho,");
        System.out.println("abys mohl ochocit legendarniho Spinosaura!");
        System.out.println("-----------------------------------------------------");
    }
}
