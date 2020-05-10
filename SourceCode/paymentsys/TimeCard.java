package paymentsys;

import java.time.LocalDateTime;

public class TimeCard {

    public final LocalDateTime date;
    public final double hours;
    
    public TimeCard(LocalDateTime date, double hours) {
        this.date = date;
        this.hours = hours;
    }
}
