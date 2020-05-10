package paymentsys;
import java.util.*;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
public class CommissionedPayment implements Payment {
	public double commission;
	public double basePay;
  public LocalDateTime commisionDate;
	private HashMap<LocalDateTime, SalesReceipt> salesReceipts =  new HashMap<LocalDateTime, SalesReceipt>();

	public CommissionedPayment(double commission, double basePay , LocalDateTime date){
		this.basePay = basePay;
		this.commission = commission;
    this.commisionDate = date;
    while(this.commisionDate.getDayOfWeek() != DayOfWeek.FRIDAY){
      this.commisionDate = this.commisionDate.plusDays(1);
    }
	}

 	public SalesReceipt getSalesReceipt(LocalDateTime date) {
      return salesReceipts.get(date);
  }

  public void addSalesReceipt(LocalDateTime date, double amount) {
      salesReceipts.put(date, new SalesReceipt(date, amount));
  }

  @Override
  public double calcPay(LocalDateTime payDate){
    return basePay;
    }

  @Override
  public double calcCommision(){
    double salary = 0; 
    for(LocalDateTime date = commisionDate; date.isAfter(commisionDate.minusDays(14));date = date.minusDays(1)) {
        if (salesReceipts.get(date) != null) {
          double amount = salesReceipts.get(date).amount;
          double commission = this.commission * amount / 100.0;
          salary += commission;
        }
    }
    return salary;
    }
}