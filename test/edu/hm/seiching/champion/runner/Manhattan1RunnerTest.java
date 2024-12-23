package edu.hm.seiching.champion.runner;

import edu.hm.cs.rs.mushrooming.arena.Arena;
import edu.hm.cs.rs.mushrooming.champion.Flat1MushroomArena;
import edu.hm.cs.rs.mushrooming.common.Cell;
import edu.hm.cs.rs.mushrooming.quest.Status;
import edu.hm.cs.rs.mushrooming.quest.TrueQuest;
import edu.hm.cs.rs.mushrooming.quest.Quest;
import org.junit.BeforeClass;
import org.junit.Test;

public class Manhattan1RunnerTest {
    @BeforeClass public static void beforeClass(){
        System.setProperty("world.size", "256");
        System.setProperty("world.height", "128");
    }

    @Test public void moveSW(){
        //arrange
        final Arena silly = new Flat1MushroomArena(new Cell(1,1,65),
                new Cell(0,0,65));

        final Quest quest = new TrueQuest(silly);
        final Manhattan1Runner sut = new Manhattan1Runner();
        final Status want = Status.Success;
        final int wantTicks = 20;

        //act
        sut.run(quest);
        final Status have = quest.status();



    }

}
