package paymentsys;
import java.io.Serializable;
import java.time.LocalDateTime;

public class SalesReceipt extends Serializable {

    public final LocalDateTime date;
    public final double amount;

    public SalesReceipt(LocalDateTime date, double amount) {
        this.date = date;
        this.amount = amount;
    }
}
