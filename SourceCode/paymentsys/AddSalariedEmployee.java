package paymentsys;

public class AddSalariedEmployee extends NewEmployeeTransaction{
	public double salary;

	public AddSalariedEmployee(int Employee_ID,String Name , boolean inUnion ,double salary, Database db){
		super(Employee_ID,Name,inUnion,db);
		this.salary = salary;
	}

	@Override
	public Payment CreatePayment(){
		return new MonthlyPayment(salary);
	}
}