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
		this.PaymentMode = 2;
		this.AccountBalance = 0;
	}
	public String getName(){
		return this.Name;
	}
	public void setPayDate(LocalDateTime date){
		this.payDate = date; 
	}
	public LocalDateTime getPayDate(){
		return this.payDate;
	}
	public String getEmployeeType(){
		return this.emptype;
	}
	public void updateSalary(LocalDateTime date){
		if(this.emptype == "Salaried"){
			MonthlyPayment p1 = (MonthlyPayment) this.empPay;
			double money = p1.calcPay(this.payDate);
			this.AccountBalance=+money;
			//System.out.println("Employee ID : ",this.employee_ID," got salary ",money);
			this.payDate = this.payDate.plusMonths(1);
		}
		else if(this.emptype == "Commision"){
			CommissionedPayment p2 = (CommissionedPayment) this.empPay;
			if (this.payDate.equals(date)){
				double money = p2.calcPay(this.payDate);
				this.AccountBalance += money;
				this.payDate = this.payDate.plusMonths(1);
			}
			if(date.equals(p2.commisionDate)){
				double commision = p2.calcCommision();
				this.AccountBalance += commision;
				p2.commisionDate = p2.commisionDate.plusDays(14);	
			}

		}
		else if(this.emptype == "Hourly" & date == this.payDate){
			HourlyPayment p3 = (HourlyPayment) this.empPay;
			if(this.payDate.equals(date)){
				double money = p3.calcPay(this.payDate);
				this.AccountBalance=+money;
				this.payDate = this.payDate.plusDays(7);
			}
		}
	}

	public double getAccountBalance(){
		return this.AccountBalance;
	}
	public boolean inUnion(){
		return this.inUnion;
	}

	public void joinUnion(){
		this.inUnion = true;
	}

	public void leaveUnion(){
		this.inUnion = false;
	}

	public int getPaymentMode(){
		return PaymentMode;
	}

	public void setPaymentMode(int mode){
		this.PaymentMode = mode;
	}
}



