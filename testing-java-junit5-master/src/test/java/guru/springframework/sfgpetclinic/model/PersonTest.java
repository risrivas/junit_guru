package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by rishi on 02/09/2021
 */
class PersonTest implements ModelTests {

    @Test
    void groupedAssertions() {
        // given
        Person person = new Person(1L, "Kent", "Beck");

        // then
        assertAll("Test Props Set",
                () -> assertEquals("Kent", person.getFirstName()),
                () -> assertEquals("Beck", person.getLastName())
        );
    }

    @Test
    void groupedAssertionMsgs() {
        // given
        Person person = new Person(1L, "Kent", "Beck");

        // then
        assertAll("Test Props Set",
                () -> assertEquals("Kent", person.getFirstName(), "First Name Failed"),
                () -> assertEquals("Beck", person.getLastName(), "Last Name Failed")
        );
    }
}