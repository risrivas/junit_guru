package guru.springframework;

/**
 * Created by rishi on 28/07/2021
 */
public class Dollar {
    int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    public void times(int multiplier) {
        amount *= multiplier;
    }
}
