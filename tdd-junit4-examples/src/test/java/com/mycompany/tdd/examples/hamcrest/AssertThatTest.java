package com.mycompany.tdd.examples.hamcrest;


/**
 * COMPARING MATCHERS – EQUALTO, IS, AND NOT
 * Set the age variable to 30 and then likewise for assertEquals and call equalTo, which here is Matcher.
 * The equalTo method takes a value.
 * If the Matcher value doesn't match the actual value,
 * then assertThat throws an AssertionError exception.
 */

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class AssertThatTest {

    /**
     *
     * @throws Exception
     * COMPARING MATCHERS – EQUALTO, IS, AND NOT
     */

    @Test
    public void verify_Matcher() throws Exception {
        int age = 30;
        assertThat(age, equalTo(30));
        assertThat(age, is(30));

        assertThat(age, not(equalTo(33)));
        assertThat(age, is(not(33)));
    }

    /**
     *
     * @throws Exception
     * COMPOUND VALUE MATCHERS – EITHER, BOTH, ANYOF, ALLOF, AND NOT
     */

    @Test
    public void verify_multiple_values() throws Exception {

        double marks = 100.00;

        assertThat(marks, either(is(100.00)).or(is(90.9)));
        assertThat(marks, both(not(99.99)).and(not(60.00)));
        assertThat(marks, anyOf(is(100.00),is(1.00),is(55.00),is(88.00),is(67.8)));

        assertThat(marks, not(anyOf(is(0.00),is(200.00))));

        assertThat(marks, not(allOf(is(1.00),is(100.00), is(30.00))));
    }

    /**
     *
     * @throws Exception
     * COLLECTION MATCHERS – HASITEM AND HASITEMS
     */
    @Test
    public void verify_collection_values() throws Exception {

        List<Double> salary =Arrays.asList(50.0, 200.0, 500.0);

        assertThat(salary, hasItem(50.00));
        assertThat(salary, hasItems(50.00, 200.00));
        assertThat(salary, hasSize(3));
        assertThat(salary, not(hasItem(1.00)));
        assertThat(salary, contains(50.0, 200.0, 500.0));
        assertThat(salary, containsInAnyOrder(200.0,50.0, 500.0));
        assertThat(salary, everyItem(greaterThan(25.0)));

    }

    /**
     *
     * @throws Exception
     * STRING MATCHERS – STARTSWITH, ENDSWITH, AND CONTAINSSTRING
     */
    @Test
    public void verify_Strings() throws Exception {
        String name = "John Deere";
        assertThat(name, startsWith("John"));
        assertThat(name, endsWith("Deere"));
        assertThat(name, containsString("De"));
        assertThat(name, containsString("Jo"));
    }
}

