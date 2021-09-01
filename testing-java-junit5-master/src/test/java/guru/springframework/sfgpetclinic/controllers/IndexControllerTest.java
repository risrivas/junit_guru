package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by rishi on 02/09/2021
 */
class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @Test
    void index() {
        assertEquals("index",controller.index());
        assertEquals("index",controller.index(), "Wrong view returned");
        assertEquals("index",controller.index(), () -> "Wrong view returned");
    }

    @Test
    void oupsHandler() {
        assertTrue("notimplemented".equals(controller.oupsHandler()), ()-> "This is some expensive " +
                "Message to build " +
                "for my test");
    }
}