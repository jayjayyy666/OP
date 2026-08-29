# ARK: Text Survival Game (Java Edition)

Jednoduchá textová/konzolová survival hra v jazyce Java inspirovaná světem *ARK: Survival Evolved*. Hráč prozkoumává divočinu, získává suroviny, vyrábí vybavení, bojuje s divokými dinosaury a snaží se přežít v nehostinném pravěkém světě.

---

## 🎮 Herní mechaniky a funkce

- **Průzkum a přežití:** Správa životů hráče (`Player`), hladu a inventáře.
- **Inventář a crafting:** Správa předmětů (`Inventory`, `ItemType`) – sběr surovin, výroba nástrojů, zbraní a sedel.
- **Dinosauři a fauna:**
    - **Býložravci a kořist:** Dodo (`Dodo`), Triceratops (`Triceratops`)
    - **Dravci a nebezpečí:** Velociraptor (`Velociraptor`), Spinosaurus (`Spinosaurus`), Tyrannosaurus Rex (`TRex`)
    - Každý dinosaurus má specifické statistiky (životy, útok, pasivní/agresivní chování).
- **Soubojový systém:** Tahové souboje s divokou zvěří s využitím zbraní a strategií.
- **Herní smyčka:** Řízena hlavní herní logikou v `GameEngine`.

---

## 📁 Struktura projektu

```text
ArkHra/
├── Main.java           # Vstupní bod aplikace (main metoda)
├── GameEngine.java     # Jádro hry, herní smyčka a zpracování příkazů
├── Player.java         # Třída hráče (statistiky, stav, akce)
├── Inventory.java      # Správa inventáře a předmětů
├── ItemType.java       # Enum / definice typů předmětů a surovin
├── Dinosaur.java       # Základní (abstraktní/nadřazená) třída pro dinosaury
├── Dodo.java           # Implementace dinosaura Dodo
├── Triceratops.java    # Implementace dinosaura Triceratops
├── Velociraptor.java   # Implementace dinosaura Velociraptor
├── Spinosaurus.java    # Implementace dinosaura Spinosaurus
├── TRex.java           # Implementace dinosaura T-Rex
└── NAVOD.txt           # Textový průvodce a instrukce ke hře
```

---

## 🚀 Požadavky a spuštění

### Požadavky:
- **Java Development Kit (JDK)**: Verze 8 nebo novější (doporučeno JDK 17 / 21)

### Kompilace ze složky projektu:
```bash
# Přechod do složky projektu a kompilace všech .java souborů
javac ArkHra/*.java
```

### Spuštění hry:
```bash
# Spuštění hlavní třídy
java ArkHra.Main
```

*(Případně lze projekt jednoduše otevřít a spustit v libovolném IDE jako IntelliJ IDEA, Eclipse nebo VS Code.)*

---

## 🕹️ Ovládání

Hra je ovládána pomocí textových příkazů zadávaných do konzole podle nabízených možností v herním menu:
- Prozkoumávání okolí a hledání surovin/dinosurů
- Zobrazení stavu postavy a inventáře
- Útok / obrana / útěk při setkání s predátorem
- Podrobný herní manuál naleznete v přiloženém souboru `ArkHra/NAVOD.txt`.

---

## 🛠️ Architektura a OOP principy

Projekt demonstruje základní i pokročilé principy objektově orientovaného programování v Javě:
- **Dědičnost a polymorfismus:** Odvození konkrétních dinosaurů (`TRex`, `Dodo`, atd.) ze společné bázové třídy `Dinosaur`.
- **Zapouzdření (Encapsulation):** Ochrana stavu hráče a inventáře přes privátní atributy a metody.
- **Separace zodpovědností:** Rozdělení herního enginu (`GameEngine`), herních entit (`Player`, `Dinosaur`) a uživatelského rozhraní (`Main`).