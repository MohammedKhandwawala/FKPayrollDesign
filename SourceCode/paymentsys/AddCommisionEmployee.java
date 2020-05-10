package paymentsys;

public class AddCommisionEmployee extends NewEmployeeTransaction{
	public double commision;
	public double basePay;

	public AddCommisionEmployee(int Employee_ID,String Name , boolean inUnion ,double commision,double basePay, Database db){
		super(Employee_ID,Name,inUnion,"Commision",db);
		this.commision = commision;
		this.basePay = basePay;
	}

	@Override
	public Payment CreatePayment(){
		return new CommissionedPayment(commision,basePay);
	}
}