package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

/**
 * Created by rishi on 15/09/2021
 */
@TestInstance(PER_CLASS)
@Tag("controllers")
public interface ControllerTests {

    @BeforeAll
    default void beforeAll() {
        System.out.println("Lets do something here");
    }
}
