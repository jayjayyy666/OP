import java.util.EnumMap;
import java.util.Map;

// Trida spravujici predmety a suroviny hrace
public class Inventory {
    private final Map<ItemType, Integer> items = new EnumMap<>(ItemType.class);

    public void add(ItemType typ, int mnozstvi) {
        if (mnozstvi <= 0) return;
        items.merge(typ, mnozstvi, Integer::sum);
    }

    public boolean has(ItemType typ, int mnozstvi) {
        return items.getOrDefault(typ, 0) >= mnozstvi;
    }

    public void remove(ItemType typ, int mnozstvi) {
        if (has(typ, mnozstvi)) {
            items.put(typ, items.get(typ) - mnozstvi);
        }
    }

    public int getAmount(ItemType typ) {
        return items.getOrDefault(typ, 0);
    }

    public void print() {
        System.out.println("=== INVENTAR ===");
        boolean prazdny = true;
        for (ItemType typ : ItemType.values()) {
            int mnozstvi = getAmount(typ);
            if (mnozstvi > 0) {
                System.out.println("- " + typ.getPopis() + ": " + mnozstvi);
                prazdny = false;
            }
        }
        if (prazdny) {
            System.out.println("(prazdny)");
        }
        System.out.println("================");
    }
}
