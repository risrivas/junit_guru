package guru.springframework;

/**
 * Created by rishi on 28/07/2021
 */
public class Dollar extends Money {
    public Dollar(int amount) {
        this.amount = amount;
    }

    @Override
    public Money times(int multiplier) {
        return new Dollar(amount * multiplier);
    }

}
