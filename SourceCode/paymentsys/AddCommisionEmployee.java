package paymentsys;
import java.time.LocalDateTime;

public class AddCommisionEmployee extends NewEmployeeTransaction{
	public double commision;
	public double basePay;
	public LocalDateTime commisionDate;

	public AddCommisionEmployee(int Employee_ID,String Name , boolean inUnion ,double commision,double basePay, LocalDateTime date,Database db){
		super(Employee_ID,Name,inUnion,"Commision",db);
		this.commision = commision;
		this.basePay = basePay;
		this.commisionDate = date;
	}

	@Override
	public Payment CreatePayment(){
		return new CommissionedPayment(commision,basePay,commisionDate);
	}
}