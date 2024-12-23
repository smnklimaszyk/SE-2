package edu.hm.seiching.champion.layered.driver;

import edu.hm.cs.rs.mushrooming.arena.Arena;
import edu.hm.cs.rs.mushrooming.champion.Flat1MushroomArena;
import edu.hm.cs.rs.mushrooming.common.Cell;
import edu.hm.cs.rs.mushrooming.quest.Quest;
import edu.hm.cs.rs.mushrooming.quest.TrueQuest;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class DriverOneTest {
    @Test public void moveNE(){
        //arrange
        final Arena silly = new Flat1MushroomArena(new Cell(128,128,65),
                new Cell(136,136,65));

        final Quest quest = new TrueQuest(silly);
        quest.commence();
        final DriverOne sut = new DriverOne(quest);
        final Cell want = quest.mushroom(0);

        sut.takeMeTo(quest.mushroom(0));

        final Cell have = quest.player();

        assertEquals(want, have);
    }
    @Test public void moveN(){
        //arrange
        final Arena silly = new Flat1MushroomArena(new Cell(128,128,65),
                new Cell(128,136,65));

        final Quest quest = new TrueQuest(silly);
        quest.commence();
        final DriverOne sut = new DriverOne(quest);
        final Cell want = quest.mushroom(0);

        sut.takeMeTo(quest.mushroom(0));

        final Cell have = quest.player();

        assertEquals(want, have);
    }
    @Test public void moveE(){
        //arrange
        final Arena silly = new Flat1MushroomArena(new Cell(128,128,65),
                new Cell(136,128,65));

        final Quest quest = new TrueQuest(silly);
        quest.commence();
        final DriverOne sut = new DriverOne(quest);
        final Cell want = quest.mushroom(0);

        sut.takeMeTo(quest.mushroom(0));

        final Cell have = quest.player();

        assertEquals(want, have);
    }

    @Test public void moveNW(){
        //arrange
        final Arena silly = new Flat1MushroomArena(new Cell(128,128,65),
                new Cell(100,136,65));

        final Quest quest = new TrueQuest(silly);
        quest.commence();
        final DriverOne sut = new DriverOne(quest);
        final Cell want = quest.mushroom(0);

        sut.takeMeTo(quest.mushroom(0));

        final Cell have = quest.player();

        assertEquals(want, have);
    }

    @Test public void moveSW(){
        //arrange
        final Arena silly = new Flat1MushroomArena(new Cell(128,128,65),
                new Cell(100,102,65));

        final Quest quest = new TrueQuest(silly);
        quest.commence();
        final DriverOne sut = new DriverOne(quest);
        final Cell want = quest.mushroom(0);

        sut.takeMeTo(quest.mushroom(0));

        final Cell have = quest.player();

        assertEquals(want, have);
    }

    @Test public void moveSE(){
        //arrange
        final Arena silly = new Flat1MushroomArena(new Cell(128,128,65),
                new Cell(140,102,65));

        final Quest quest = new TrueQuest(silly);
        quest.commence();
        final DriverOne sut = new DriverOne(quest);
        final Cell want = quest.mushroom(0);

        sut.takeMeTo(quest.mushroom(0));

        final Cell have = quest.player();

        assertEquals(want, have);
    }
}
