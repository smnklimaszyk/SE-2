package edu.hm.seiching.champion.layered.planner;

import edu.hm.cs.rs.mushrooming.arena.Arena;
import edu.hm.cs.rs.mushrooming.champion.FlatMushroomsArena;
import edu.hm.cs.rs.mushrooming.common.Cell;
import edu.hm.cs.rs.mushrooming.quest.Quest;
import edu.hm.cs.rs.mushrooming.quest.TrueQuest;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PlannersTest {


    @Test public void testGetMushroomList(){
        //Arrange
        final List<Cell> mushrooms = new ArrayList<>();
        mushrooms.add(new Cell(0,40,65));
        mushrooms.add(new Cell(0,30, 65));
        mushrooms.add(new Cell(0,10, 65));
        mushrooms.add(new Cell(0,20, 65));
        final Arena arena = new FlatMushroomsArena(new Cell(0,0, 65), mushrooms, 65);
        final Quest quest = new TrueQuest(arena);
        quest.commence();
        final List<Cell> want = new ArrayList<>();
        want.add(new Cell(0,40,65));
        want.add(new Cell(0,30, 65));
        want.add(new Cell(0,10, 65));
        want.add(new Cell(0,20, 65));


        //Act
        Planners sut = new Planners();
        final List<Cell> have = sut.getMushroomList(quest);

        //Assert
        assertEquals(want, have);
    }


    @Test public void testFlatDistance(){
        //Arrange
        Cell from = new Cell(0,0,65);
        Cell to = new Cell(5,7,65);
        int want = 5*Quest.DIAGONAL_FRACTIONS + 2*Quest.ORTHO_FRACTIONS;

        Planners sut = new Planners();
        int have = sut.flatDistance(from, to);

        assertEquals(want, have);
    }



}
