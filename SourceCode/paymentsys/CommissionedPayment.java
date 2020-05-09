package paymentsys;
import java.util.*;
import java.time.LocalDateTime;

public class CommissionedPayment implements Payment {
	public double commission;
	private HashMap<LocalDateTime, SalesReceipt> salesReceipts;

	public CommissionedPayment(double commission){
		this.commission = commission;
	}

 	public SalesReceipt getSalesReceipt(LocalDateTime date) {
      return salesReceipts.get(date);
  }

  public void addSalesReceipt(LocalDateTime date, double amount) {
      salesReceipts.put(date, new SalesReceipt(date, amount));
  }

  @Override
  public double calcPay(LocalDateTime payDate){
    double salary = 0; 
    for(LocalDateTime date = payDate; date.isAfter(payDate.minusDays(1));date = date.minusDays(1)) {
        if (salesReceipts.get(date) != null) {
          double amount = salesReceipts.get(date).amount;
          double commission = this.commission * amount / 100.0;
          salary += commission;
        }
      }
    return salary;
    }
}