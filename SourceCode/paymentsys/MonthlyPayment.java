package paymentsys;

import java.time.LocalDateTime;

public class MonthlyPayment implements Payment {
	public double salary;

	public MonthlyPayment(double salary){
		this.salary = salary;
	}

	@Override
	public double calcPay(LocalDateTime payDate){
		return salary;
	}
}