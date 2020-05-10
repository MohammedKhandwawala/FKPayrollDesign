package paymentsys;
import java.io.Serializable;
import java.time.LocalDateTime;

public interface Payment extends Serializable {
	double calcPay(LocalDateTime payDate);
	double calcCommision();
}