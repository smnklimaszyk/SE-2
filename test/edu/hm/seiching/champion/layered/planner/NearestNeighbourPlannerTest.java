package edu.hm.seiching.champion.layered.planner;


import edu.hm.cs.rs.mushrooming.arena.Arena;
import edu.hm.cs.rs.mushrooming.champion.Flat1MushroomArena;
import edu.hm.cs.rs.mushrooming.champion.FlatMushroomsArena;
import edu.hm.cs.rs.mushrooming.common.Cell;
import edu.hm.cs.rs.mushrooming.quest.Quest;
import edu.hm.cs.rs.mushrooming.quest.TrueQuest;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighbourPlannerTest {




    @Test public void testPlanOneMushroom() {
        final Arena arena = new Flat1MushroomArena(new Cell(1,1,65), new Cell(0,0,65));
        final Quest quest = new TrueQuest(arena);
        final NearestNeighbourPlanner sut = new NearestNeighbourPlanner(quest);
        quest.commence();
        List<Cell> have = sut.plan();

        List<Cell> want = new ArrayList<>();
        want.add(quest.mushroom(0));
        want.addLast(quest.player());

        assertEquals(want,have);

    }

    @Test public void testPlanMoreMushrooms() {
        final List<Cell> mushrooms = new ArrayList<>();
        mushrooms.add(new Cell(0,40,65));
        mushrooms.add(new Cell(0,30, 65));
        mushrooms.add(new Cell(0,10, 65));
        mushrooms.add(new Cell(0,20, 65));

        final Arena arena = new FlatMushroomsArena(new Cell(0,0, 65), mushrooms, 65);
        final Quest quest = new TrueQuest(arena);
        quest.commence();

        List<Cell> want = new ArrayList<>();
        want.add(new Cell(0,10, 65));
        want.add(new Cell(0,20, 65));
        want.add(new Cell(0,30, 65));
        want.add(new Cell(0,40,65));
        want.addLast(quest.spawn());

        final Planner sut = new NearestNeighbourPlanner(quest);
        List<Cell> have = sut.plan();

        assertEquals(want, have);

    }

    @Test
    public void testNearestNeighbourHappy(){
        //Arrange
        //Arrange
        final List<Cell> mushrooms = new ArrayList<>();
        mushrooms.add(new Cell(0,40,65));
        mushrooms.add(new Cell(0,30, 65));
        mushrooms.add(new Cell(0,10, 65));
        mushrooms.add(new Cell(0,20, 65));
        final Arena arena = new FlatMushroomsArena(new Cell(0,0, 65), mushrooms, 65);
        final Quest quest = new TrueQuest(arena);
        quest.commence();

        Cell targetCell = new Cell(0,21, 65);
        Cell want = new Cell(0,20, 65);

        //Act
        NearestNeighbourPlanner sut = new NearestNeighbourPlanner(quest);
        Cell have = sut.findNearestNeighbour(targetCell, mushrooms);

        //Assert
        assertEquals(want, have);
    }

    @Test
    public void testNearestNeighbourSamePosition(){
        //Arrange
        final List<Cell> mushrooms = new ArrayList<>();
        mushrooms.add(new Cell(0,40,65));
        mushrooms.add(new Cell(0,30, 65));
        mushrooms.add(new Cell(0,10, 65));
        mushrooms.add(new Cell(0,20, 65));
        final Arena arena = new FlatMushroomsArena(new Cell(0,0, 65), mushrooms, 65);
        final Quest quest = new TrueQuest(arena);
        quest.commence();


        Cell targetCell = new Cell(0,20, 65);
        Cell want = new Cell(0,30, 65);

        //Act
        NearestNeighbourPlanner sut = new NearestNeighbourPlanner(quest);
        Cell have = sut.findNearestNeighbour(targetCell, mushrooms);

        //Assert
        assertEquals(want, have);
    }
}
