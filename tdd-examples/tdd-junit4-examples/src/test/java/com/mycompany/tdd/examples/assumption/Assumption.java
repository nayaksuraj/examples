package com.mycompany.tdd.examples.assumption;

import org.junit.Test;
import org.junit.Assume;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Assumption {

    boolean isSomeServiceRunning = false;

    @Test
    public void testCheckServerisRunning() throws Exception {
        isSomeServiceRunning = false;
        Assume.assumeFalse(isSomeServiceRunning);
        assertTrue(true);
    }

}
