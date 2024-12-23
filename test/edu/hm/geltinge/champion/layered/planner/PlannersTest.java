package edu.hm.geltinge.champion.layered.planner;

import edu.hm.cs.rs.mushrooming.arena.Arena;
import edu.hm.cs.rs.mushrooming.champion.FlatMushroomsArena;
import edu.hm.cs.rs.mushrooming.common.Cell;
import edu.hm.cs.rs.mushrooming.quest.Quest;
import edu.hm.cs.rs.mushrooming.quest.TrueQuest;
import edu.hm.geltinge.champion.layered.planner.AnnealingPlanner;
import edu.hm.geltinge.champion.layered.planner.NearestNeighbourPlanner;
import edu.hm.geltinge.champion.layered.planner.Planners;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PlannersTest {

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
        sut = new Planners();
        quest.commence();
    }


    private List<Cell> mushrooms;
    private Quest quest;
    private Planners sut;

    @Test public void testGetMushroomList(){
        //Arrange
        final List<Cell> want = new ArrayList<>();
        want.add(new Cell(0,40,65));
        want.add(new Cell(0,30, 65));
        want.add(new Cell(0,10, 65));
        want.add(new Cell(0,20, 65));
        //Act
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

    @Test public void testCompletePath() {
        // Arrange
        List<Cell> want = new ArrayList<>();
        want.add(new Cell(1,1,66));
        want.add(new Cell(0,40,65));
        want.add(new Cell(0,30, 65));
        want.add(new Cell(0,10, 65));
        want.add(new Cell(0,20, 65));
        want.add(new Cell(1,1,65));

        // Act
        List<Cell> have = sut.completePath(mushrooms, quest);

        //Assert
        assertEquals(want, have);

    }

    @Test public void testCalcPathLen() {
        // Arrange
        int want = 40 * Quest.ORTHO_FRACTIONS;

        // Act
        int have = sut.calcPathLength(mushrooms);

        assertEquals(want, have);

    }

    @Test
    public void testComparator() {
        // Arrange
        final TreeSet<List<Cell>> sortedPaths = new TreeSet<>(Planners.pathComparator);
        final List<Cell> want = mushrooms;

        List<Cell> expensiveWayOne = new ArrayList<>();
        expensiveWayOne.add(new Cell(0,0,65));
        expensiveWayOne.add(new Cell(0,40,65));
        expensiveWayOne.add(new Cell(0,10, 65));
        expensiveWayOne.add(new Cell(0,30, 65));
        expensiveWayOne.add(new Cell(0,20, 65));
        sortedPaths.add(expensiveWayOne);

        List<Cell> expensiveWayTwo = new ArrayList<>();
        expensiveWayTwo.add(new Cell(0,0,65));
        expensiveWayTwo.add(new Cell(0,30, 65));
        expensiveWayTwo.add(new Cell(0,10,65));
        expensiveWayTwo.add(new Cell(0,40,65));
        expensiveWayTwo.add(new Cell(0,20, 65));
        sortedPaths.add(expensiveWayTwo);

        sortedPaths.add(want);

        // Act
        List<Cell> have = Collections.min(sortedPaths, sut.pathComparator);

        // Assert
        assertEquals(want, have);
    }

    @Test public void testFindNearestNeighbour() {
        //Arrange
        Cell targetCell = new Cell(0,21, 65);
        Cell want = new Cell(0,20, 65);
        //Act
        Cell have = sut.findNearestNeighbour(targetCell, mushrooms);

        //Assert
        assertEquals(want, have);
    }

    @Test
    public void testNearestNeighbourSamePosition(){
        //Arrange
        Cell targetCell = new Cell(0,20, 65);
        Cell want = new Cell(0,30, 65);

        //Act
        Cell have = sut.findNearestNeighbour(targetCell, mushrooms);

        //Assert
        assertEquals(want, have);
    }
    @Test
    public void testRawPath() {
        // Arrange
        List<Cell> want = new ArrayList<>();
        want.add(new Cell(0,30, 65));
        want.add(new Cell(0,10, 65));

        // Act
        List<Cell> have = Planners.rawPath(mushrooms);
        //Assert
        assertEquals(have, want);
    }


    @Test
    public void testGetFirstNPathsSet() {
        Set<List<Cell>> mushroomSet = new TreeSet<>(Planners.pathComparator);

        List<Cell> mushrooms2 = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            mushrooms2.add(new Cell(0,i * 20, 65));
        }

        List<Cell> mushrooms3 = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            mushrooms3.add(new Cell(0,i * 30, 65));
        }

        List<Cell> mushrooms4 = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            mushrooms4.add(new Cell(0,i * 40, 65));
        }

        List<Cell> mushrooms5 = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            mushrooms5.add(new Cell(0,i * 50, 65));
        }

        mushroomSet.add(mushrooms2);
        mushroomSet.add(mushrooms3);
        mushroomSet.add(mushrooms4);
        mushroomSet.add(mushrooms5);

        Set<List<Cell>> want = new TreeSet<>(Planners.pathComparator);
        want.add(mushrooms2);
        want.add(mushrooms3);

        Set<List<Cell>> have = Planners.getFirstNPaths(mushroomSet, 2, () -> new TreeSet<>(Planners.pathComparator));


        assertEquals(want, have);

    }
}



