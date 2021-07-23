package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by rishi on 24/07/2021
 */
class FooTest {

    @Test
    void getBar() {
        Foo foo = new Foo();
        assertEquals("FooBar", foo.getBar());
    }
}