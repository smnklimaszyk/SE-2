package edu.hm.geltinge.champion.layered.planner;


import edu.hm.cs.rs.mushrooming.arena.Arena;
import edu.hm.cs.rs.mushrooming.champion.Flat1MushroomArena;
import edu.hm.cs.rs.mushrooming.champion.FlatMushroomsArena;
import edu.hm.cs.rs.mushrooming.common.Cell;
import edu.hm.cs.rs.mushrooming.quest.Quest;
import edu.hm.cs.rs.mushrooming.quest.TrueQuest;
import edu.hm.geltinge.champion.layered.planner.AnnealingPlanner;
import edu.hm.geltinge.champion.layered.planner.NearestNeighbourPlanner;
import edu.hm.geltinge.champion.layered.planner.Planner;
import edu.hm.geltinge.champion.layered.planner.Planners;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.hm.geltinge.champion.layered.planner.AnnealingPlanner.generateRandomSolution;
import static org.junit.Assert.*;

public class AnnealingPlannerTest {
    @Before
    public void setUp() {
        mushrooms = new ArrayList<>();
        mushrooms.add(new Cell(0,40,65));
        mushrooms.add(new Cell(0,30, 65));
        mushrooms.add(new Cell(0,10, 65));
        mushrooms.add(new Cell(0,20, 65));
        mushrooms.add(new Cell(0,90, 65));
        mushrooms.add(new Cell(0,85, 65));
        mushrooms.add(new Cell(0,80, 65));
        mushrooms.add(new Cell(0,75, 65));
        mushrooms.add(new Cell(0,70, 65));
        mushrooms.add(new Cell(0,65, 65));
        mushrooms.add(new Cell(0,60, 65));
        mushrooms.add(new Cell(0,55, 65));

        Arena arena = new FlatMushroomsArena(
                new Cell(0, 0, 65),
                mushrooms,
                65
        );

        quest = new TrueQuest(arena);
        sut = new AnnealingPlanner(quest);
        quest.commence();
    }


    private List<Cell> mushrooms;
    private Quest quest;
    private Planner sut;



    @Test
    public void testQuest() {
        // Arrange
        Quest want = quest;
        //Act
        Quest have = sut.quest();
        // Assert
        assertEquals(want, have);
    }


    @Test
    public void testPlan() {
        // Arrange
        int want = 850;

        // Act
        List<Cell> calculatedPath = sut.plan();
        int have = Planners.calcPathLength(calculatedPath);
        System.out.println(have);

        //Assert
        assertTrue(have <= want);
    }

    @Test
    public void testGenerateRandomSolution() {
        //Arrange
        List<Cell> want = mushrooms;

        // Act
        List<Cell> have = generateRandomSolution(mushrooms);


        //Assert
        assertNotEquals(want, have);
    }



}
