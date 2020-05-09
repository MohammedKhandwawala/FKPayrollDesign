package paymentsys;

public class Employee {
	public String Name;
	public int employee_ID;
	public Payment empPay;
	public boolean inUnion;
	public double payDate;
	public double AccountBalance;

	public Employee(int empId, String name){
		this.employee_ID = empId;
		this.Name = name;
	}
}



