package com.mycompany.tdd.examples.suittest;

import org.junit.runner.RunWith;
        import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClassA.class,
        ClassB.class
})
public class SuiteMultipleTest {
    //normally, this is an empty class
}