package cabinvoice;

public class InvoiceService {

    public double calculateFare(RideType type, double distance, int time) {
        double totalFare = distance * type.minimumCostPerKM + time * type.costPerTime;
        return Math.max(type.minFare, totalFare);
    }

    //to generate invoice at the end of the month
    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.rideType, ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRide(String userId, Ride[] rides) {
        RideRepository.addRides(userId, rides);
    }

    //to get the invoice summary of a user
    public InvoiceSummary getInvoiceSummary(String userId) {
        Ride[] rides = RideRepository.getRides(userId);
        return this.calculateFare(rides);
    }
}