import cabinvoice.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceService = null;
    private RideRepository rideRepository = null;

    @Before
    public void setUp() throws Exception {
        invoiceService = new InvoiceService();
        invoiceService = new InvoiceService();
        rideRepository = new RideRepository();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2;
        int time = 5;
        double fare = invoiceService.calculateFare(RideType.NORMAL, distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(RideType.NORMAL, distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void giveMultipleRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(RideType.NORMAL, 2.0, 5),
                new Ride(RideType.PREMIUM, 2, 5),
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 65.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void giveUserIdWithMultipleRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(RideType.NORMAL, 2.0, 5),
                new Ride(RideType.PREMIUM, 2, 5),
        };
        invoiceService.addRide("mounika", rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary("mounika");
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 65.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenNormalUserId_GenerateTotalFare_ShouldReturnInvoiceSummery() {
        Ride[] rides = {new Ride(RideType.NORMAL, 35.0, 45),
                new Ride(RideType.NORMAL, 10.55, 30),
                new Ride(RideType.NORMAL, 20, 30)
        };
        invoiceService.addRide("mounika", rides);
        InvoiceSummary invoiceSummery = invoiceService.getInvoiceSummary("mounika");
        InvoiceSummary expectedSummery = new InvoiceSummary(3, 760.5);
        Assert.assertEquals(expectedSummery, invoiceSummery);
    }

    @Test
    public void givenDistanceAndTime_IfTotalFareWrong_shouldThrowAssertionError() {
        double distance = 3;
        int time = 4;
        double fare = invoiceService.calculateFare(RideType.NORMAL, distance, time);
        Assert.assertNotEquals(20, fare, 0.0);
    }

    @Test
    public void giveMultipleRides_IfInvoiceSummaryWrong_shouldThrowAssertionError() {
        Ride[] rides = {new Ride(RideType.NORMAL, 2.0, 5),
                new Ride(RideType.NORMAL, 2, 5),
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 67.0);
        Assert.assertNotEquals(expectedInvoiceSummary, summary);
    }
}
