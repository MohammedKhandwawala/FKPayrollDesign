import paymentsys.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.io.*;

public class PaymentSystem{
	public static void main(String[] args){
		LocalDateTime date = LocalDateTime.now();
		Database db = new Database();
		int stop = 0;
		int arg ;
		int empID = 0;
		double unionCharge = 100;
		while(stop != 1){
			System.out.println("Select One of the option");
			System.out.println("\n1) Add an Employee");
			System.out.println("2) Show Employee Detail");
			System.out.println("3) Delete Employee");
			System.out.println("4) Increment Time");
			System.out.println("5) Add timeCard for an Employee");
			System.out.println("6) Add SalesReceipt for an Employee");
			System.out.println("7) Modify Employee Pay");
			System.out.println("8) Close Session");
			System.out.println("9) Change Union Charge");
			System.out.println("10) Save Data");
			System.out.println("11) Get Data");
			Scanner sc = new Scanner(System.in);
			arg = sc.nextInt();
			if (arg == 1){
				//Create new employee
				//jav
				Scanner str = new Scanner(System.in);
				System.out.println("Select Employee Type");
				System.out.println("\n1) Salaried Employee");
				System.out.println("2) Hourly Employee");
				int emp_arg = sc.nextInt();
				String empName;
				if(emp_arg == 1){
					System.out.printf("\nEmployee ID is %d\n",empID);
					System.out.println("Enter Employee Name");
					empName = str.nextLine();
					System.out.println(empName);
					System.out.println("Is Employee Union Member yes/no");
					boolean isUnion = false;
					String resp = str.nextLine();
					if (resp.equals("yes"))
						isUnion = true;
					System.out.println("Is employee eligible for Commision yes/no");
					String reply = str.nextLine();
					Double comm = 0.0;
					if(reply.equals("yes")){
						System.out.println("Enter Employee Commision Rate");
						comm = str.nextDouble();
					}
					System.out.println("Enter Employee Basic Salary");
					int base_sal = str.nextInt();
					System.out.println("Enter Payment Mode (0- paychecks mailed ,1 - paychecks held for pickup , 2 - Direct Deposit");
					int payMode = str.nextInt();
					AddSalariedEmployee emp = new AddSalariedEmployee(empID, empName,isUnion,comm,base_sal,date,db);
					emp.make();
					Employee emp1 = db.getEmployee(empID);
					emp1.setPaymentMode(payMode);
					emp1.payDate = date;
					while(emp1.payDate.getDayOfMonth() != 30){
						emp1.payDate = emp1.payDate.plusDays(1);
					}
					empID+=1;
				}
				else if(emp_arg == 2){
					System.out.printf("Employee ID is %d\n",empID);
					System.out.println("Enter Employee Name");
					empName = str.nextLine();
					System.out.println("Is Employee Union Member yes/no");
					boolean isUnion = false;
					String resp = str.nextLine();
					if (resp.equals("yes"))
						isUnion = true;
					System.out.println("Enter Employee Hourly Salary");
					int sal = sc.nextInt();
					System.out.println("Enter Payment Mode (0- paychecks mailed ,1 - paychecks held for pickup , 2 - Direct Deposit");
					int payMode = sc.nextInt();
					AddHourlyEmployee emp = new AddHourlyEmployee(empID, empName,isUnion,sal,db);
					emp.make();
					Employee emp1 = db.getEmployee(empID);
					emp1.setPaymentMode(payMode);
					emp1.payDate = date;
					while(emp1.payDate.getDayOfWeek() != DayOfWeek.FRIDAY){
						emp1.payDate = emp1.payDate.plusDays(1);
					}
					empID+=1;
				}
				else{
					System.out.println("No Input");
				}
				//
			}
			else if(arg == 2){
				//Employee Detail Printer
				//
				//
				System.out.println("Enter EmployeeID of the Employee for Details");
				int in = sc.nextInt();
				Employee emp_t = db.getEmployee(in);
				if(emp_t == null){
					System.out.println("Invalid EmployeeID");
				}
				else{
					System.out.printf("Name of the Employee %s\n",emp_t.getName());
					System.out.printf("Member of Union %B\n",emp_t.inUnion());
					System.out.printf("Account Balance %f\n",emp_t.getAccountBalance());
					System.out.printf("Pay Day ");
					System.out.println(emp_t.getPayDate());
					System.out.printf("Payment Mode %d\n",emp_t.getPaymentMode());
					Payment p = emp_t.empPay;
					if(p instanceof SalariedPayment){
						SalariedPayment mp = (SalariedPayment) p;
						if(mp.commission == 0)
							System.out.printf("Employee is Salary Employee with base Payment of %f\n",mp.basePay);
						else
							System.out.printf("Employee is Commision based with base Payment of %f and Commision of %f\n",mp.basePay, mp.commission);
					}
					else{
						HourlyPayment hp = (HourlyPayment) p;
						System.out.printf("Employee is Hourly Paid at a rate of %f\n",hp.hourlyRate);
					}
				}
				//
				//
			}
			else if(arg == 3){
				//Delete Employee
				//
				System.out.println("Enter Employee ID to Delete");
				int in = sc.nextInt();
				boolean check = db.deleteEmployee(in);
				if(!check){
					System.out.println("Invalid EmployeeID");
				}
				//
			}
			else if(arg == 4){
				System.out.println("Next day");
				System.out.println(date);
				date = date.plusDays(1);
				if(date.getDayOfMonth() == 30){
					for(int i = 0 ; i < empID ; i++){	
						Employee emp = db.getEmployee(i);
						emp.updateSalary(date);
						emp.unionPayment(unionCharge);
					}
				}
				else{
					for(int i = 0 ; i < empID ; i++){	
						Employee emp = db.getEmployee(i);
						emp.updateSalary(date);
					}
				}
			}
			else if(arg == 5){
				System.out.println("Enter Employee ID for Adding timeCard");
				int id = sc.nextInt();
				Employee emp = db.getEmployee(id);
				if (emp.emptype.equals("Hourly")){
					HourlyPayment p = (HourlyPayment) emp.empPay;
					System.out.println("Enter Hours Worked");
					double hrs = sc.nextDouble();
					p.addTimeCard(date,hrs);
				}
				else{
					System.out.println("Incorrect Type");
				}
			}
			else if(arg == 6){
				System.out.println("Enter Employee ID for Adding Sales Receipt");
				int id = sc.nextInt();
				Employee emp = db.getEmployee(id);
				if (emp.emptype.equals("Salaried")){
					SalariedPayment p = (SalariedPayment) emp.empPay;
					System.out.println("Enter amount :");
					double amt = sc.nextDouble();
					p.addSalesReceipt(date,amt);
				}
				else{
					System.out.println("Incorrect Type");
				}
			}
			else if(arg == 7){
				System.out.println("Enter Id of employee to change pay");
				int empid = sc.nextInt();
				Employee emp = db.getEmployee(empid);
				System.out.printf("Type of employee is %s",emp.emptype);
				if(emp.emptype.equals("Hourly")){
					System.out.println("Enter Hourly Rate");
					double hr = sc.nextDouble();
					HourlyPayment hp = (HourlyPayment) emp.empPay;
					hp.hourlyRate = hr;
				} 
				else if(emp.emptype.equals("Salaried")){
					System.out.println("Enter Base pay");
					double bp = sc.nextDouble();
					System.out.println("Enter Commision");
					double comm = sc.nextDouble();
					SalariedPayment cp = (SalariedPayment) emp.empPay;
					cp.basePay = bp;
					cp.commission = comm; 
				}
			}
			else if(arg == 8){
				stop = 1;
			}
			else if(arg == 9){
				System.out.println("Enter the new monthly Union Charge");
				unionCharge = sc.nextInt();
			}
			else if(arg == 10){
				//save session date
				try{
				FileOutputStream dt = new FileOutputStream("EmployeeData/date.ser");
        ObjectOutputStream os = new ObjectOutputStream(dt);
        os.writeObject(date);
        os.close();
        dt.close();
      	}catch(Exception e){
      		System.out.println("Cant find file to  write");
      	}
      	try{
				FileOutputStream dt = new FileOutputStream("EmployeeData/empid.ser");
        ObjectOutputStream os = new ObjectOutputStream(dt);
        os.writeObject(empID);
        os.close();
        dt.close();
      	}catch(Exception e){
      		System.out.println("Cant find file to  write");
      	}
      	//save Union Details
				try{
				FileOutputStream udt = new FileOutputStream("EmployeeData/UnionData.ser");
        ObjectOutputStream os1 = new ObjectOutputStream(udt);
        os1.writeObject(unionCharge);
        os1.close();
        udt.close();
      	}catch(Exception e){
      		System.out.println("Cant find file to  write");
      	}
      	//Database save
				try{
					for(int i = 0; i < empID ; i++){
						FileOutputStream fos = new FileOutputStream("EmployeeData/data"+String.valueOf(i)+".ser");
	        	ObjectOutputStream oos = new ObjectOutputStream(fos);
	        	oos.writeObject(db.getEmployee(i));
	        	oos.close();
	        	fos.close();
	        }
      	}catch(Exception e){
      		System.out.println(e);
      	}
			}	
			else if(arg == 11){
				//read date
				try{
					FileInputStream dt = new FileInputStream("EmployeeData/date.ser");
	        ObjectInputStream os = new ObjectInputStream(dt);
	        date = (LocalDateTime) os.readObject();
	        System.out.println(date);
	        os.close();
	        dt.close();
      	}catch(Exception e){
      		System.out.println("Cant find file to read");
      	}
      	try{
					FileInputStream dt = new FileInputStream("EmployeeData/empid.ser");
	        ObjectInputStream os = new ObjectInputStream(dt);
	        empID = (Integer) os.readObject();
	        System.out.printf("Number of Employees %d\n",empID);
	        os.close();
	        dt.close();
      	}catch(Exception e){
      		System.out.println("Cant find file to read");
      	}
				//read session details
				try{
					FileInputStream udt = new FileInputStream("EmployeeData/UnionData.ser");
	        ObjectInputStream os1 = new ObjectInputStream(udt);
	        unionCharge = (Double) os1.readObject();
	        os1.close();
	        udt.close();
      	}catch(Exception e){
      		System.out.println("Cant find file to read");
      	}
				//Read Database
				try{
					for(int i = 0; i < empID ; i++){
						FileInputStream fis = new FileInputStream("EmployeeData/data"+String.valueOf(i)+".ser");
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        Employee check = (Employee) ois.readObject();
		        System.out.println(check.Name);
		        db.addEmployee(i,check);
		        ois.close();
		        fis.close();
	      	}
      	}catch(Exception e){
      		System.out.println(e);
      	}
			} 
			else{
				System.out.println("Invalid Input");
			}
		}
	}
}