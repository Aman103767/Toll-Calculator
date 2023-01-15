import java.io.FileInputStream; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class Geektrust {

	private static int totalToll;
	private static int totalDiscountGiven;
	private static int fastAmountCollected;
	private static int cashAmountCollected;
	private static List<VehicleToll> vehicleTollList = new ArrayList<>();
	// here we are creating the list vehicle and there total toll collected.


	
	public static Map<String,Integer> vehiclesDetails(){
		// here we are mapping the vehicle and there toll.
		Map<String,Integer>vehiclesDetails = new HashMap<>();
		vehiclesDetails.put("TRUCK",200);
		vehiclesDetails.put("BUS",200);
		vehiclesDetails.put("VAN",100);
		vehiclesDetails.put("CAR",100);
		vehiclesDetails.put("RICKSHAW",100);
		vehiclesDetails.put("SCOOTER",50);
		vehiclesDetails.put("MOTORBIKE",50);
		return vehiclesDetails;

	}

	public static Map<String,Integer> numberPlateMap(List<String> numberPlateList){
		// Here we are find the frequency of each Number Plate of the vehicle.
		Map<String,Integer> map = new LinkedHashMap<>();
		for(String i: numberPlateList) {
			if(map.containsKey(i)) {
				map.put(i, map.get(i)+1);
			}else {
				map.put(i, 1);
			}
		}
		return map;



	}
	public static int passWithFastag(int fastagBalance,String vehicle,Map<String,Integer> vehicleDetails) {

		int tollForVehicle = vehicleDetails.get(vehicle);
		// Above line will get the toll for particular vehicle.
		int countVehicle = 0;
		// Above line counting the NumberofVehicle.
		int oddAndEven = 0;
		if(tollForVehicle>fastagBalance) {
			// here we don't have enough fastag balance than no vehicle will pass the toll with fastag.
			return 0;
		}else {
			// here we are checking how many vehicle can pass with fastag.
			while(tollForVehicle<=fastagBalance || oddAndEven%2==1 ) {
				if(oddAndEven%2 == 0) {
					fastagBalance -= tollForVehicle;
					countVehicle++;
				}else if(tollForVehicle/2<=fastagBalance) {
					fastagBalance -= tollForVehicle/2;
					countVehicle++;
				}

				oddAndEven++;
			}
		}
		return countVehicle;

	}
	public static void perVechicleToll(String vehicle,int toll) {
		// Here we will add the vehicle and there toll paid in vehicleAndTotalAmountList.

		for(VehicleToll v : vehicleTollList) {
			if(v.getVehicle().equals(vehicle)){
				// if there is vehicle already exist in list then we have to update the toll.
				v.setTotalAmount(v.getTotalAmount()+toll);
				return;
			}
		}

		vehicleTollList.add(new VehicleToll(vehicle,toll));
		// Above line will add the vehicle and there toll in the list.
	}
	public static void updatingValue(Map<String,Integer> fastTagBalance,int fullPrice,int discountedPrice,int noOfVehicle,int flatFee,
			List<String>vehicleList,int index, Map.Entry<String, Integer> m,Map<String,Integer> vehicleDetails ) {
		if(fastTagBalance.containsKey(m.getKey())) {

			if(fullPrice+discountedPrice > fastTagBalance.get(m.getKey())) {
				// Here we are checking total price for that particular vehicle to pay the toll is greater than balance it has in there fastag.

				int passWithFastag = passWithFastag(fastTagBalance.get(m.getKey()),vehicleList.get(index), vehicleDetails);
				// Above line will give you the total vehicle passes with fastag balance only.

				fastAmountCollected += fastTagBalance.get(m.getKey());
				// Above line will add total amount collected by the fasttag that vehicle have.

				int passWithoutFastTag =	noOfVehicle-passWithFastag;
				// Above line will give total number of vehicle need to pass without fasttag.

				cashAmountCollected+= passWithoutFastTag*flatFee+(fullPrice+discountedPrice)-fastTagBalance.get(m.getKey());
				// Above line will give the total price need to pay in cash including 40 fee for cash flat fee charge.

				totalToll+= fullPrice+discountedPrice+(noOfVehicle-passWithFastag)*flatFee;
				// Above line will add the total toll in total.
				fastTagBalance.put(m.getKey(),0);
				// Above line will make the fasttag balance 0 because total price need to pay is greater than balance in fasttag.
			}else {
				// Here we our fastag balance is greater so we will pay with fastag only.
				fastAmountCollected += fullPrice+discountedPrice;
				fastTagBalance.put(m.getKey(), fastTagBalance.get(m.getKey())-(fullPrice+discountedPrice));
				// Above code is updating the fastag Balance.
				totalToll+= fullPrice+discountedPrice;
				// Above code to add all the price in total.
			}
		}else {
			// Here we don't have the fastag for this vehicle so we will pay with cash.
			cashAmountCollected+=fullPrice+discountedPrice+noOfVehicle*flatFee;
			// Above line will give total amount collected with cash.
			totalToll+= fullPrice+discountedPrice+noOfVehicle*flatFee;
			// We are adding same in total.
		}

	}

	public static void calculateToll(Map<String,Integer> fastTagBalance,List<String> vehicleList,List<String> NumberPlateList) {
		// This method does all the calculation for toll and return total toll for all the vehicle.


		Map<String,Integer> vehicleDetails = vehiclesDetails();
		Map<String,Integer> numberPlateMap = numberPlateMap(NumberPlateList);
		//The above line will give the map with frequency of numberPlate.
		int flatFee = 40;
		int two = 2;

		int index = 0;
		for(Map.Entry<String, Integer> m: numberPlateMap.entrySet()) {


			int passVehicle = m.getValue();
			// The above code will give number of vehicle need to pass.

			// there is mainly two logic for even and odd number of vehicle need to pass.
			if(passVehicle%two == 0) {

				int vehicleToll = vehicleDetails.get(vehicleList.get(index));
				int fullPrice = (passVehicle/two*vehicleToll);
				// The above line will give total price that vehicle have to pay without any discount;
				int discountedPrice = (passVehicle/two*(vehicleToll/two));
				// The above line will give total price that vehicle have to pay with discount;
				totalDiscountGiven+= discountedPrice;
				// Here we are adding the discountedPrice to total DiscountGiven.
				perVechicleToll(vehicleList.get(index),fullPrice+discountedPrice);
				// The above line will create object of which vehicle and how much total toll we collect with the vehicle.

				updatingValue(fastTagBalance,fullPrice, discountedPrice, passVehicle,flatFee,
						vehicleList, index,m,vehicleDetails );

			}else {
				int vehicleToll = vehicleDetails.get(vehicleList.get(index));
				int discountedPrice = (((int)(passVehicle/two))*(vehicleToll/two));
				// The above line will give total price that vehicle have to pay with discount;

				int fullPrice = (((int)(passVehicle/two)+1)*vehicleToll);
				// The above line will give total price that vehicle have to pay without any discount;

				totalDiscountGiven+= discountedPrice;
				// Here we are adding the discountedPrice to total DiscountGiven.

				perVechicleToll(vehicleList.get(index),fullPrice+discountedPrice);
				// The above line will create object of which vehicle and how much total toll we collect with the vehicle.

				updatingValue(fastTagBalance,fullPrice, discountedPrice, passVehicle,flatFee,
						vehicleList, index,m,vehicleDetails );
			}
			index++;

		}
	}

	public static List<String> managingElement(List<String> vehicleList,List<String> numberPlateList){
		// this method will manage the element like in list the arragement of the list of collect_toll_vehicle.
		Map<String,Integer> map = numberPlateMap(numberPlateList);
		List<String>  vehicleListSec = new ArrayList<>();
		int index = 0;
		for(Map.Entry<String, Integer> m: map.entrySet()) {
			String plate = m.getKey();
			char vehicle = plate.charAt(index);
			switch(vehicle) {
			case 'T':
				vehicleListSec.add("TRUCK");
				break;
			case 'B':
				vehicleListSec.add("BUS");
				break;
			case 'V':
				vehicleListSec.add("VAN");
				break;
			case 'C':
				vehicleListSec.add("CAR");
				break;
			case 'R':
				vehicleListSec.add("RICKSHAW");
				break;
			case 'S':
				vehicleListSec.add("SCOOTER");
				break;
			case 'M':
				vehicleListSec.add("MOTORBIKE");
				break;
			}

		}
		return vehicleListSec;

	}


	public static String numberOfVehicle(List<String> vehicleList ) {
		// this method will give the vehicle and number of vehicle in sorted form in string.below is the example 
		//CAR 3
		//BUS 1
		//TRUCK 1
		Map<String,Integer> map = new LinkedHashMap<>();
		for(String i: vehicleList) {
			if(map.containsKey(i)) {
				map.put(i, map.get(i)+1);
			}else {
				map.put(i, 1);
			}
		}
		Collections.sort(vehicleTollList,new Comparator<VehicleToll>() {
			@Override
			public int compare(VehicleToll v1 , VehicleToll v2) {
				if(v1.getTotalAmount()>v2.getTotalAmount()) {
					return -1;
				}else if(v1.getTotalAmount()<v2.getTotalAmount()){
					return 1;
				}else {
					return v1.getVehicle().compareTo(v2.getVehicle());
				}
			}
		});

		String vehicleCount  = "";


		for(VehicleToll v :  vehicleTollList) {
			vehicleCount += v.getVehicle()+" "+map.get(v.getVehicle())+"\n";
		}

		return vehicleCount;


	}
	public static void printSummary(List<String> vehicleList) {

		System.out.println("TOTAL_COLLECTION "+totalToll+" "+totalDiscountGiven);
		System.out.println("PAYMENT_SUMMARY "+fastAmountCollected+" "+cashAmountCollected);
		System.out.println("VEHICLE_TYPE_SUMMARY");
		String vehicleCount = numberOfVehicle(vehicleList);
		System.out.println(vehicleCount);
	}
	
	public static void main(String[] args)  {
		try {
			// the file to be opened for reading
			String fileLocation = "sample_input\\input1.txt";
			FileInputStream fis = new FileInputStream(fileLocation);
			Scanner sc = new Scanner(fis); // file to be scanned
			// returns true if there is another line to read


			Map<String,Integer> fastTagBalance = new LinkedHashMap<>();

			List<String> vehicleList = new ArrayList<>();
			List<String> numberPlateList = new ArrayList<>();
			
			while (sc.hasNextLine()) {

				String fastTag = "FASTAG";
				String collectToll = "COLLECT_TOLL";
				String fastTagOrCollectToll = sc.next();

				if(fastTagOrCollectToll.equals(fastTag)) {
					String vehicleNumberPlate = sc.next();
					int fastagBalance = sc.nextInt();

					fastTagBalance.put(vehicleNumberPlate,fastagBalance);

				} else if(fastTagOrCollectToll.equals(collectToll)) {
					String vehicle = sc.next();
					String numberPlate = sc.next();

					vehicleList.add(vehicle);
					numberPlateList.add(numberPlate);
				}


			}
			List<String> vehicleListSec =  managingElement(vehicleList,numberPlateList);

			calculateToll(fastTagBalance,vehicleListSec,numberPlateList);

			printSummary(vehicleList);


			sc.close(); // closes the scanner
		} catch (IOException e) {
		}
	}
}
