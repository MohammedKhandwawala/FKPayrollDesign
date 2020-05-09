package paymentsys;

import java.time.LocalDateTime;

public interface Payment{
	double calcPay(LocalDateTime payDate);
}