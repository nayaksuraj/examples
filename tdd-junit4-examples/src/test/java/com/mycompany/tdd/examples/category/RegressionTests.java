package com.mycompany.tdd.examples.category;


import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RegressionTests {

    @Category(RegressionCategoryTests.class)

    @Test
    public void reg_test1() {
        assertThat(1 == 1, is(true));
    }

    @Test
    public void reg_test2() {
        assertThat(1 == 1, is(true));
    }

    @Test
    public void reg_test3() {
        assertThat(1 == 1, is(true));
    }

}