package com.mycompany.tdd.examples.category;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(RegressionCategoryTests.class)
@Suite.SuiteClasses({PerformanceTests.class, RegressionTests.class, MultipleTests.class})
public class RegressionTestSuite {
}
