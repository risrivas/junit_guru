package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Created by rishi on 02/09/2021
 */
class IndexControllerTest implements ControllerTests {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Test Proper View name is returned for index page")
    @Test
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "Wrong view returned");
        assertEquals("index", controller.index(), () -> "Wrong view returned");

        assertThat(controller.index()).isEqualTo("index");
    }

    @Test
    @DisplayName("Test exception")
    void oupsHandler() {
        assertThrows(ValueNotFoundException.class, () -> {
            controller.oopsHandler();
        });
//        assertTrue("notimplemented".equals(controller.oopsHandler()), ()-> "This is some expensive " +
//                "Message to build " +
//                "for my test");
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeOut() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);
            System.out.println("I got here");
        });
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeOutPreempt() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);
            System.out.println("I got here 123414341");
        });
    }

    @Test
    void testAssumptionTrue() {
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @Test
    void testAssumptionTrueAssumptionIsTrue() {
        assumeTrue("GURU".equalsIgnoreCase("GURU"));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMacOS() {

    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows() {

    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8() {

    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testMeOnJava11() {

    }

    @EnabledIfEnvironmentVariable(named = "username", matches = "rishi")
    @Test
    void testIfUserRishi() {

    }

    @EnabledIfEnvironmentVariable(named = "username", matches = "fred")
    @Test
    void testIfUserFred() {

    }


}