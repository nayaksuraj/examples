package com.mycompany.tdd.examples.category;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MultipleTests {

    @Category({PerformanceCategoryTests.class, RegressionCategoryTests.class})

    @Test
    public void multiple_category_1() {
        assertThat(1 == 1, is(true));
    }

    @Test
    public void multiple_category_2() {
        assertThat(1 == 1, is(true));
    }

}