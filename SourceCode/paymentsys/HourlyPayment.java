package paymentsys;
import java.util.*;
import java.time.LocalDateTime;

public class HourlyPayment implements Payment {
	public double hourlyRate;
	private HashMap<LocalDateTime, TimeCard> timecards = new HashMap<LocalDateTime,TimeCard>();

	public HourlyPayment(double hourlyRate){
		this.hourlyRate = hourlyRate;
	}

 	public TimeCard getTimeCard(LocalDateTime date) {
      return timecards.get(date);
  }

  public void addTimeCard(LocalDateTime date, double amount) {
      timecards.put(date, new TimeCard(date, amount));
  }

  @Override
  public double calcPay(LocalDateTime payDate){
    double salary = 0; 
    for(LocalDateTime date = payDate; date.isAfter(payDate.minusDays(7));date = date.minusDays(1)) {
        if (timecards.get(date) != null) {
          double hours = timecards.get(date).hours;
          salary += hourlyRate * hours;
          if (hours > 8.0)
            salary += hourlyRate * (hours - 8) / 2.0;
        }
      }
      return salary;
    }
    
 @Override
  public double calcCommision(){
    return 0;
  }
}