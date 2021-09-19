package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

/**
 * Created by rishi on 19/09/2021
 */
@Tag("repeated")
public interface ModelRepeatedTests {
    @BeforeEach
    default void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.printf("(%s, %d)%n", testInfo.getDisplayName(), repetitionInfo.getCurrentRepetition());
    }
}
