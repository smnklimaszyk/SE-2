package edu.hm.cs.rs.mushrooming.champion;

import edu.hm.cs.rs.mushrooming.arena.Arena;
import edu.hm.cs.rs.mushrooming.common.Block;
import edu.hm.cs.rs.mushrooming.common.Cell;

/** Komplett flache Arena mit 1 Pilz.
 * @author R. Schiedermeier
 * @version 2024-03-21
 * @param spawn Wo Steve startet und ankommt.
 * @param mushroom Wo der einzige Pilz steht.
 * @param altitude Auf welcher Hoehe die Oberflaeche liegt.
 */
public record Flat1MushroomArena(Cell spawn, Cell mushroom, int altitude) implements Arena {
    public Flat1MushroomArena(Cell spawn, Cell mushroom) {
        this(spawn, mushroom, 64);
    }

    @Override public Block block(Cell cell) {
        return cell.altitude() > altitude? Block.Air: Block.Stone;
    }

    @Override public int mushrooms() {
        return 1;
    }

    @Override public Cell mushroom(int index) {
        return mushroom;
    }
}
