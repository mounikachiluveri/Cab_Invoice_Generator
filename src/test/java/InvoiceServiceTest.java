import cabinvoice.InvoiceGenerator;
import org.junit.Assert;
import org.junit.Test;

public class InvoiceServiceTest {
    @Test
    public void givendistanceAndTime_ShouldReturnTotalFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }
}
