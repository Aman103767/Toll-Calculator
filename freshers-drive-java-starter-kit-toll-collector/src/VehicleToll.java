
public class VehicleToll {
	private String vehicle;
	private int totalAmount;
	

	
	
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	@Override
	public String toString() {
		return "VehicleAndTotalAmount [vehicle=" + vehicle + ", totalAmount=" + totalAmount + "]";
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public VehicleToll() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VehicleToll(String string, int i) {
		// TODO Auto-generated constructor stub
		this.vehicle = string;
		this.totalAmount = i;
	}

}
