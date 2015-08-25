import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Rule;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

@RunWith(JUnit4.class)
public class XsBeforeOsTest {
        
    @Rule
    public ExpectedException exception = ExpectedException.none();
        
    @Test
    public void isOK1() {
        char[] arr = {'X', 'X', 'O', 'O', 'O'};

        XsBeforeOs.isOK(arr, 1);
        XsBeforeOs.isOK(arr, 2);
        XsBeforeOs.isOK(arr, arr.length - 1);
    }
    
    @Test
    public void isOK2() {
        char[] arr = {'X', 'X', 'O', 'X', 'O'};

        XsBeforeOs.isOK(arr, 1);
        XsBeforeOs.isOK(arr, 2);
        
        exception.expect(IllegalStateException.class);
        XsBeforeOs.isOK(arr, 3);
        
        exception.expect(IllegalStateException.class);
        XsBeforeOs.isOK(arr, arr.length - 1);
    }

    @Test
    @Ignore
    public void rearrange1() {
        char[] arr = {'X', 'X', 'O', 'O', 'O'};
        
        XsBeforeOs.rearrange(arr);
    }

    @Test
    @Ignore
    public void rearrange2() {
        char[] arr = {'X', 'X', 'O', 'X', 'O'};

        XsBeforeOs.rearrange(arr);
    }

}
