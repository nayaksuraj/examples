package com.mycompany.tdd.examples.timeout;

import org.junit.Test;

/**
    If a test is taking longer than a defined “timeout” to finish,
    a TestTimedOutException will be thrown and the test marked failed.
    This timeout example only applies to a single test method.
    And the timeout value is in milliseconds.
*/

public class TimeoutTest {

    //This test will always failed :)
    @Test(timeout = 1000)
    public void infinity() {
       // while (true) ;
    }

    //This test can't run more than 5 seconds, else failed
    @Test(timeout = 5000)
    public void testSlowMethod() {
        //...
    }

}