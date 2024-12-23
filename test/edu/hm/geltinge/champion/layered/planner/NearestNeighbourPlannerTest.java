package edu.hm.geltinge.champion.layered.planner;


import edu.hm.cs.rs.mushrooming.arena.Arena;
import edu.hm.cs.rs.mushrooming.champion.Flat1MushroomArena;
import edu.hm.cs.rs.mushrooming.champion.FlatMushroomsArena;
import edu.hm.cs.rs.mushrooming.common.Cell;
import edu.hm.cs.rs.mushrooming.quest.Quest;
import edu.hm.cs.rs.mushrooming.quest.TrueQuest;

import edu.hm.geltinge.champion.layered.planner.NearestNeighbourPlanner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighbourPlannerTest {
    @Before
    public void setUp() {
        mushrooms = new ArrayList<>();
        mushrooms.add(new Cell(0,40,65));
        mushrooms.add(new Cell(0,30, 65));
        mushrooms.add(new Cell(0,10, 65));
        mushrooms.add(new Cell(0,20, 65));

        Arena arena = new FlatMushroomsArena(
                new Cell(1, 1, 65),
                mushrooms,
                65
        );

        quest = new TrueQuest(arena);
        sut = new NearestNeighbourPlanner(quest);
        quest.commence();
    }


    private List<Cell> mushrooms;
    private Quest quest;
    private NearestNeighbourPlanner sut;


    @Test public void testQuest() {
        // Arrange
        Quest want = quest;

        // Act
        Quest have = sut.quest();

        //Assert
        assertEquals(want, have);
    }





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
        // Arrange
        List<Cell> want = new ArrayList<>();
        want.add(new Cell(0,10, 65));
        want.add(new Cell(0,20, 65));
        want.add(new Cell(0,30, 65));
        want.add(new Cell(0,40,65));
        want.add(new Cell(1, 1, 65));

        // act
        List<Cell> have = sut.plan();

        // Assert
        assertEquals(want, have);

    }
}
