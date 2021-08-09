package guru.springframework;

/**
 * Created by rishi on 10/08/2021
 */
public abstract class Money {
    protected int amount;

    public abstract Money times(int multiplier);

    public static Money dollar(int amount) {
        return new Dollar(amount);
    }

    public static Money franc(int amount) {
        return new Franc(amount);
    }

    @Override
    public boolean equals(Object o) {
        Money that = (Money) o;
        return that.amount == this.amount
                && this.getClass().equals(that.getClass());
    }
}
