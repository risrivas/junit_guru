package guru.springframework;

/**
 * Created by rishi on 28/07/2021
 */
public class Franc extends Money {
    public Franc(int amount) {
        this.amount = amount;
    }

    @Override
    public Money times(int multiplier) {
        return new Franc(amount * multiplier);
    }

}
