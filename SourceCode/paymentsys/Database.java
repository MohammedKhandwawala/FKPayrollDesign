package paymentsys;

import java.util.*;

public class Database{
	private static HashMap<Integer, Employee> employees = new HashMap<Integer,Employee>();
	
	public void addEmployee(int id, Employee employee){
		employees.put(id, employee);	
	}	

	public Employee getEmployee(int id){
		return employees.get(id);
	}

	public boolean deleteEmployee(int id){
		if (employees.containsKey(id) == true){
			employees.remove(id);
			return true;
		}
		else{
			return false;
		}
	}
}