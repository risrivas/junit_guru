package guru.springframework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by rishi on 28/07/2021
 */
public class MoneyTest {

    @Test
    @DisplayName("$5 * 2 = $10")
    void testMultiplication() {
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);
        assertEquals(10, product.getAmount());

        product = five.times(3);
        assertEquals(15, product.getAmount());
    }
}
