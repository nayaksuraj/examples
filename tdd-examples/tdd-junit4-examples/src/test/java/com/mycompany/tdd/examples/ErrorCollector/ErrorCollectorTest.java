package com.mycompany.tdd.examples.ErrorCollector;

import org.junit.rules.ErrorCollector;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Rule;
import org.junit.Test;

public class ErrorCollectorTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void fails_after_execution() {
        collector.checkThat("b", equalTo("b"));
        collector.checkThat(2, equalTo(2));
        collector.checkThat("g", equalTo("g"));
    }
}