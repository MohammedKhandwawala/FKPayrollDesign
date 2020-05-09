package paymentsys;

public abstract class NewEmployeeTransaction {
	
	private int employee_ID;
	private String name;
	private boolean inUnion;
	private Database db;

	public NewEmployeeTransaction (int employee_ID,String name,boolean inUnion,Database db){
		this.employee_ID = employee_ID;
    this.name = name;
    this.db = db;
    this.inUnion = inUnion;
	}

	public abstract Payment CreatePayment();

	public void make(){
		Payment pt = CreatePayment();
		Employee emp = new Employee(employee_ID,name);
		emp.empPay = pt;
		emp.inUnion = inUnion; 
		db.addEmployee(employee_ID,emp);
	}
}