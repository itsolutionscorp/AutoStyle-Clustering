import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

@RunWith(JUnit4.class)
public class DateTest {
        
    @Rule
    public ExpectedException exception = ExpectedException.none();
        
    @Test
    public void oldYear() {
        Date date = new Date(1, 1, 1800);

        exception.expect(IllegalStateException.class);
        date.isOK();
    }
    
    @Test
    public void futureYear() {
        Date date = new Date(1, 1, 2200);

        exception.expect(IllegalStateException.class);
        date.isOK();
    }

    @Test
    public void validYear() {
        Date date = new Date(6, 6, 2014);

        date.isOK();
    }

    @Test
    public void leapYear() {
        Date date = new Date(2, 29, 2008);

        date.isOK();
    }

    @Test
    public void notLeapYear() {
        Date date = new Date(2, 29, 2014);

        exception.expect(IllegalStateException.class);
        date.isOK();
    }

    @Test
    public void thirtyOneDays() {
        Date date = new Date(7, 31, 2014);

        date.isOK();
    }

    @Test
    public void notThirtyOneDays() {
        Date date = new Date(6, 31, 2014);

        exception.expect(IllegalStateException.class);
        date.isOK();
    }

    @Test
    public void negativeDate() {
        Date date = new Date(6, -1, 2014);

        exception.expect(IllegalStateException.class);
        date.isOK();
    }

    @Test
    public void negativeMonth() {
        Date date = new Date(-4, 1, 2014);

        exception.expect(IllegalStateException.class);
        date.isOK();
    }

    @Test
    public void negativeYear() {
        Date date = new Date(6, 1, -1000);

        exception.expect(IllegalStateException.class);
        date.isOK();
    }

}
