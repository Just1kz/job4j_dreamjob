package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Test1Test {

    @Test
    public void aga() {
        assertThat(Test1.aga(), is("aga"));
    }
}