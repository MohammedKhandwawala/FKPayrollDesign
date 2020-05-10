package paymentsys;
import java.io.Serializable;
import java.time.LocalDateTime;

public class TimeCard extends Serializable{

    public final LocalDateTime date;
    public final double hours;
    
    public TimeCard(LocalDateTime date, double hours) {
        this.date = date;
        this.hours = hours;
    }
}
