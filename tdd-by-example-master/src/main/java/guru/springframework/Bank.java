package guru.springframework;

/**
 * Created by rishi on 11/08/2021
 */
public class Bank {
    public Money reduce(Expression source, String toCurrency) {
        return source.reduce(toCurrency);
    }
}
