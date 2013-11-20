package com.amitcodes.spring.example;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OrchestraTheaterTest {

    private static final Logger logger = Logger.getLogger(OrchestraTheater.class.getName());

    @Test
    public void testBroadcastPerformance() {
        OrchestraTheater theater = new OrchestraTheater();
        String broadcast = theater.broadcastPerformance();
        String expectedBroadcast =  "Drum playing\n" +
                                    "Violin playing\n" +
                                    "Flute playing\n" +
                                    "Vocalist: Bohemian Rhapsody";
        logger.log(Level.INFO, "[broadcast]:\n{0}", broadcast);
        Assert.assertEquals(expectedBroadcast, broadcast);
    }
}
