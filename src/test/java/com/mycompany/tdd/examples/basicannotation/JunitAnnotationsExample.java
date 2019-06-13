package com.mycompany.tdd.examples.basicannotation;

import com.mycompany.tdd.examples.assertions.ArrayAssertionDemo;
import org.junit.*;

    public class JunitAnnotationsExample {

    // Run once, e.g. Database connection, connection pool
    @BeforeClass
    public static void trunOnceBeforeClass() {
        System.out.println("@BeforeClass - runOnceBeforeClass");
    }

    // Run once, e.g close connection, cleanup
    @AfterClass
    public static void runOnceAfterClass() {
        System.out.println("@AfterClass - runOnceAfterClass");
    }

    // Should rename to @BeforeTestMethod
    // e.g. Creating an similar object and share for all @Test
    @Before
    public void runBeforeTestMethod() {
        System.out.println("@Before - runBeforeTestMethod");
    }

    // Should rename to @AfterTestMethod
    @After
    public void runAfterTestMethod() {
        System.out.println("@After - runAfterTestMethod");
    }

    @Test
    public void TestsomeMethod1() {
        System.out.println("@Test - test_method_1");
    }

    @Ignore("Reason: why do you want to ignore?")
    public void TestIgnoreMethod() {
        System.out.println("Using @Ignore , this execution is ignored");
    }

    @Test
    public void TestsomeMethod2() {
        System.out.println("@Test - test_method_2");
    }

    @Test(timeout = 10)
    public void whenExecutionTimeReached_thenShouldBeTimeout() {
        while(true);
    }

    @Test(expected = NullPointerException.class)
    public void whenExceptionThrown_thenExpectationSatisfied() {
        String somenullvalue = null;
        somenullvalue.length();

    }
}