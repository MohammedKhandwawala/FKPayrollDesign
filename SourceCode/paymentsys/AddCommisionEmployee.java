package paymentsys;

public class AddCommisionEmployee extends NewEmployeeTransaction{
	public double commision;

	public AddCommisionEmployee(int Employee_ID,String Name , boolean inUnion ,double commision, Database db){
		super(Employee_ID,Name,inUnion,db);
		this.commision = commision;
	}

	@Override
	public Payment CreatePayment(){
		return new CommissionedPayment(commision);
	}
}