package com.mycompany.tdd.examples.assertions;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;

public class ArrayAssertionTest {

    @Test
    public void twoIntArraysShouldBeEqual() {

        ArrayAssertionDemo expectedvalue = new ArrayAssertionDemo();

        int[] expected = expectedvalue.getIntArray();
        int[] actual = new int[]{2, 5, 7};

        assertArrayEquals(expected, actual);
        assertArrayEquals(
                String.format("Expected array: %s to be equal to array: %s but arrays are not equal",
                        Arrays.toString(expected),
                        Arrays.toString(actual)
                ),
                expected,
                actual
        );
    }

    @Test
    public void twoStringArraysShouldBeEqual() {

        ArrayAssertionDemo expectedvalue = new ArrayAssertionDemo();

        String[] expected = expectedvalue.getStringArray();
        String[] actual = new String[] {"foo", "bar"};

        assertArrayEquals(expected, actual);
        assertArrayEquals(
                String.format("Expected array: %s to be equal to array: %s but arrays are not equal",
                        Arrays.toString(expected),
                        Arrays.toString(actual)
                ),
                expected,
                actual
        );
    }
}