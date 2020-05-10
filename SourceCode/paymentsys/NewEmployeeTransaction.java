package paymentsys;

public abstract class NewEmployeeTransaction {
	
	private int employee_ID;
	private String name;
	private boolean inUnion;
	private Database db;
	private String empType;

	public NewEmployeeTransaction (int employee_ID,String name,boolean inUnion,String empType,Database db){
		this.employee_ID = employee_ID;
    this.name = name;
    this.db = db;
    this.inUnion = inUnion;
    this.empType = empType;
	}

	public abstract Payment CreatePayment();

	public void make(){
		Payment pt = CreatePayment();
		Employee emp = new Employee(employee_ID,name);
		emp.empPay = pt;
		emp.inUnion = inUnion; 
		emp.emptype = empType;
		db.addEmployee(employee_ID,emp);
	}
}