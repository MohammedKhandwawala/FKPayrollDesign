package paymentsys;
import java.time.LocalDateTime;

public class Employee {
	public String Name;
	public int employee_ID;
	public Payment empPay;
	public boolean inUnion;
	public LocalDateTime payDate;
	public double AccountBalance;
	public int PaymentMode;
	public String emptype;

	public Employee(int empId, String name){
		this.employee_ID = empId;
		this.Name = name;
	}
}



