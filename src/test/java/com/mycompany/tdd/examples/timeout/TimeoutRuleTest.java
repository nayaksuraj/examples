package com.mycompany.tdd.examples.timeout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class TimeoutRuleTest {

    @Rule
    public Timeout globalTimeout =  new Timeout(20);

    @Test
    public void testInfiniteLoop1() throws InterruptedException{
        Thread.sleep(30);
    }

    @Test
    public void testInfiniteLoop2() throws InterruptedException{
        Thread.sleep(30);
    }

}