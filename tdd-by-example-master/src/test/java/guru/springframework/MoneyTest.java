package guru.springframework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Created by rishi on 28/07/2021
 */
public class MoneyTest {

    @Test
    @DisplayName("$5 * 2 = $10 (Dollar)")
    void testMultiplicationDollar() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    @DisplayName("Equality test (Dollar)")
    void testEqualityDollar() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(8));
    }

    @Test
    @DisplayName("5 CHF * 2 = 10 CHF (Franc)")
    void testMultiplicationFranc() {
        Money five = Money.franc(5);
        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));
    }

    @Test
    @DisplayName("Equality test (Franc)")
    void testEqualityFranc() {
        assertEquals(Money.franc(5), Money.franc(5));
        assertNotEquals(Money.franc(5), Money.franc(8));
    }

    @Test
    @DisplayName("Equality test - Dollar and Franc")
    void testEqualityDollarAndFranc() {
        assertNotEquals(Money.dollar(5), Money.franc(5));
    }

}
