package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.*;

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

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
    @DisplayName("My Repeated Test")
    void myRepeatedTest() {
        // no impl
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
    }
}