import paymentsys.*;

public class test{
	public static void main(String[] args){
		//Employee created
		Employee emp1 = new Employee(1,"mohammed");
		Employee emp2 = new Employee(2,"rohit");
		System.out.println("Employee Created ..");
		System.out.println(emp1.Name);
		Database db = new Database();
		System.out.println("\nAdded Employees to database");
		db.addEmployee(1,emp1);
		db.addEmployee(2,emp2);
		System.out.println("\nRetrieved Employee from database");
		Employee emp_t = db.getEmployee(1);
		System.out.println(emp_t.Name);
		System.out.println("\n Removing Employee from database");
		db.deleteEmployee(2);
		emp_t = db.getEmployee(2);
		System.out.println(emp_t);
	}
}