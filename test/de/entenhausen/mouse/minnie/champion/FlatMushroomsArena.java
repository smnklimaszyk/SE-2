package de.entenhausen.mouse.minnie.champion;

import edu.hm.cs.rs.mushrooming.arena.Arena;
import edu.hm.cs.rs.mushrooming.common.Block;
import edu.hm.cs.rs.mushrooming.common.Cell;
import java.util.Collections;
import java.util.List;

/**
 * Komplett flache Arena mit mehreren Pilzen.
 * @param spawn       Wo Steve startet und ankommt.
 * @param mushroomsAt Wo die Pilze stehen.
 * @param altitude    Auf welcher Hoehe die Oberflaeche liegt.
 * @author R. Schiedermeier
 * @version 2024-03-21
 */
public record FlatMushroomsArena(Cell spawn, List<Cell> mushroomsAt, int altitude) implements Arena {
    public FlatMushroomsArena {
        if(mushroomsAt.isEmpty())
            throw new IllegalArgumentException("at least 1 mushroom required");
        mushroomsAt = Collections.unmodifiableList(mushroomsAt);
    }

    public FlatMushroomsArena(Cell spawn, Cell... mushrooms) {
        this(spawn, List.of(mushrooms), 64);
    }

    @Override public Block block(Cell cell) {
        return cell.altitude() > altitude? Block.Air: Block.Stone;
    }

    @Override public int mushrooms() {
        return mushroomsAt.size();
    }

    @Override public Cell mushroom(int index) {
        return mushroomsAt.get(index);
    }
}
