package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

/**
 * Created by rishi on 15/09/2021
 */
@Tag("model")
public interface ModelTests {
    @BeforeEach
    default void beforeEach(TestInfo testInfo) {
        System.out.printf("Running test (%s)%n", testInfo.getDisplayName());
    }
}
