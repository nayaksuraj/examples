package com.mycompany.tdd.examples.category;


import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PerformanceTests {

    @Category(PerformanceCategoryTests.class)

    @Test
    public void perf_tes1() {
        assertThat(1 == 1, is(true));
    }

    @Test
    public void perf_test2() {
        assertThat(1 == 1, is(true));
    }



}