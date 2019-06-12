package com.mycompany.tdd.examples.suittest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClassB {
    @Test
    public void twoStringShouldBeEqual(){
        assertEquals("hello","hello");
    }
}
