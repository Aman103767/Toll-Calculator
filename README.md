# Toll-Calculator

FASTAG
 The construction company prefers money to be collected via FASTAG. FASTAG is an electronic payment utility that can be used to pay tolls.  All vehicles have a vehicle number. The FASTAG is associated with a vehicle number.
 If there is no sufficient balance in the FASTAG, then the remaining cost of the toll needs to be paid by cash. If travellers do not have a FASTAG they can make the payment by Cash. There is a flat fee of 40 for such cash transactions.
 
### Toll charges
 Amount of toll collected varies based on the weight of the vehicle.
 
 ![vehicle-types](https://user-images.githubusercontent.com/54835356/212551138-0bf2929a-8e7f-4954-b2e6-deb1f9c399c6.png)
 
 ### Journey Type
 Toll charges are different for a single trip and for a return journey. When a vehicle does a return journey, there is a discount of 50% for the return journey.
For eg: If a Van passes through the toll first time, the toll collected is 100. If the same Van passes again through the toll, the amount collected for the return journey is 50. If the Van passes a third time, it will be treated as a new single journey and the toll collected is 100. 
Goal
 Your task is to build a solution that calculates various tolls collected and print the payment summary and vehicle type summary.
 
 The payment summary should give a breakup of the total amount collected and the total discount given. 
 The vehicle type summary should display the total number of vehicles passed per type in descending order of the collection amount.
### Assumptions
 All vehicles passing through has a vehicle number. 
 Some vehicles may not have FASTAG, in that case the toll amount is paid by cash. 
 Some vehicles that have FASTAG may not have sufficient money to pay the toll, then the remaining amount is paid by cash. 
 Tolls are always collected from FASTAG first, then only cash is taken if needed. 
 Every cash transaction has a flat fee of 40. 
 Cash amount collected includes the toll charges paid and the flat fee of 40. 
 The vehicle count is calculated based on journeys. eg: if the same car passes twice, the count is 2.
 ## Sample I/O
 Input Commands & Format
 Your program should take as input the FASTAG details of each vehicle and the list of vehicles that passed through the toll.
 
FASTAG <VEHICLE_NUMBER> <FASTAG_BALANCE>
 
 <VEHICLE_NUMBER> is the identifier for a given vehicle. 
 <FASTAG_BALANCE> is the amount of money available in the vehicle’s FASTAG balance
 
COLLECT_TOLL <VEHICLE_TYPE> <VEHICLE_NUMBER>
 
 <COLLECT_TOLL> command should deduct the appropriate amount of toll from the FASTAG of the vehicle, depending on the vehicle type.
 
PRINT_COLLECTION
 
 Prints the calculated toll collection summary as  
 TOTAL_COLLECTION 
 PAYMENT_SUMMARY 
 VEHICLE_TYPE_SUMMARY
 Output Commands & Format
 Your program should print the payment summary and vehicle type summary in the following format.
 
TOTAL_COLLECTION <TOTAL_AMOUNT_OF_TOLL_COLLECTED> <TOTAL_DISCOUNT_GIVEN>
 
PAYMENT_SUMMARY <FASTAG_AMOUNT_COLLECTED> <CASH_AMOUNT_COLLECTED>
 
VEHICLE_TYPE_SUMMARY
 <VEHICLE_TYPE_WITH_HIGHEST_COLLECTION> <VEHICLE_PASSED_COUNT>
 <VEHICLE_TYPE_WITH_SECOND_HIGHEST_COLLECTION> <VEHICLE_PASSED_COUNT>
 .
 .
 .
 <VEHICLE_TYPE_WITH_LEAST_COLLECTION> <VEHICLE_PASSED_COUNT>
 
 VEHICLE_TYPE_SUMMARY is sorted by amount collected first, then vehicle name. ie, If the toll collection amount is the same, then VEHICLE_TYPE_SUMMARY should list the vehicles in alphabetical order.
 ### INPUT
 FASTAGC1N1 200
<br> FASTAGT1N1 500 
<br> FASTAGC1N2 50 
<br> FASTAGB1N1 500 
<br> COLLECT_TOLLCAR C1N1 
<br> COLLECT_TOLLTRUCK T1N1 
<br> COLLECT_TOLLCAR C1N2 
<br> COLLECT_TOLLBUS B1N1 
<br> COLLECT_TOLLCAR C1N3 
<br> PRINT_COLLECTION
### OUTPUT
TOTAL_COLLECTION780 0
<br> PAYMENT_SUMMARY550 230
<br> VEHICLE_TYPE_SUMMARY
<br> CAR 3
<br> BUS 1
<br> TRUCK 1
