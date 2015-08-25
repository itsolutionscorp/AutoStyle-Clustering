import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;


public class AccountTest extends TestCase {
    public void testInit() {
    		Account ex1 = new Account(100);
    		assertTrue(ex1.balance() == 100);
    }
    
    public void testInvalidArgs(){
    	Account ex2 = new Account(10);
    	ex2.withdraw(-50);
    	assertTrue(ex2.balance() == 10);
    	ex2.deposit(-10);
    	assertTrue(ex2.balance() == 10);
    }
    
    public void testOverdraft() {
    	Account ex3 = new Account(100);
    	Account ex4 = new Account(50, ex3);
    	ex3.withdraw(200);
    	assertTrue(ex3.balance() == 100);
    }

    public void testDeposit(){
    	Account ex5 = new Account(100);
    	ex5.deposit(10);
    	assertTrue(ex5.balance() == 110);
    }
    public void testWithdraw(){
    	Account ex6 = new Account(100);
    	ex6.withdraw(50);
    	assertTrue(ex6.balance() == 50);
    }

}
