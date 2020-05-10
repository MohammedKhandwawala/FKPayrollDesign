package paymentsys;

public class AddHourlyEmployee extends NewEmployeeTransaction{
	public double hourlyRate;

	public AddHourlyEmployee(int Employee_ID,String Name , boolean inUnion ,double hourlyRate, Database db){
		super(Employee_ID,Name,inUnion,"Hourly",db);
		this.hourlyRate = hourlyRate;
	}

	@Override
	public Payment CreatePayment(){
		return new HourlyPayment(hourlyRate);
	}
}