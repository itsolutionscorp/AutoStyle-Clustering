import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
    public void testConstructor() {
    	Measurement m = new Measurement();
        assertTrue (m.getFeet() == 0);
        assertTrue (m.getInches() == 0);
    }
    
    public void testOneArgCtor() { // Ctor = constructor
    	Measurement m = new Measurement(7);
        assertTrue (m.getFeet() == 7);
        assertTrue (m.getInches() == 0);
    }
    
    public void testOneArgCtor2() { // does not need conversion 
    	Measurement m = new Measurement(700);
        assertTrue (m.getFeet() == 700);
        assertTrue (m.getInches() == 0);
        
    }
    
    public void testTwoArgCtor() { // Ctor = constructor
    	Measurement m = new Measurement(7, 4);
        assertTrue (m.getFeet() == 7);
        assertTrue (m.getInches() == 4);
    }
    
    public void testTwoArgCtor2() { // needs conversion
    	Measurement m = new Measurement(14, 14);
        assertTrue (m.getFeet() == 15);
        assertTrue (m.getInches() == 2);
    }
    
    public void testTwoArgCtor3() { // needs conversion, even crazier 
    	Measurement m = new Measurement(2, 700);
        assertTrue (m.getFeet() == 60);
        assertTrue (m.getInches() == 4);
    }
    
    public void testGetFeet() {
    	Measurement m = new Measurement(7, 4);
        assertTrue (m.getFeet() == 7);
    }
    
    public void testGetInches() {
    	Measurement m = new Measurement(7, 4);
        assertTrue (m.getInches() == 4);
    }
    
    public void testPlus() {
    	Measurement m = new Measurement(7, 4);
    	Measurement m2 = new Measurement(5, 9);
    	Measurement m3 = m.plus(m2);
    	assertTrue (m3.getFeet() == 13);
        assertTrue (m3.getInches() == 1);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 7); 
        assertEquals (m.getInches(), 4); 
        assertEquals (m2.getFeet(), 5); 
        assertEquals (m2.getInches(), 9); 
    }
    
    public void testPlus2() { // normal
    	Measurement m = new Measurement(7, 4);
    	Measurement m2 = new Measurement(5, 2);
    	Measurement m3 = m.plus(m2);
    	assertTrue (m3.getFeet() == 12);
        assertTrue (m3.getInches() == 6);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 7); 
        assertEquals (m.getInches(), 4); 
        assertEquals (m2.getFeet(), 5); 
        assertEquals (m2.getInches(), 2); 
    }
    
    public void testPlus3() { // even crazier
    	Measurement m = new Measurement(7, 4);
    	Measurement m2 = new Measurement(100, 100);
    	Measurement m3 = m.plus(m2);
    	assertTrue (m3.getFeet() == 115);
        assertTrue (m3.getInches() == 8);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 7); 
        assertEquals (m.getInches(), 4); 
        assertEquals (m2.getFeet(), 108); 
        assertEquals (m2.getInches(), 4); 
    }
    
    public void testPlus4() { // 0
    	Measurement m = new Measurement(0, 4);
    	Measurement m2 = new Measurement(0, 0);
    	Measurement m3 = m.plus(m2);
    	assertTrue (m3.getFeet() == 0);
        assertTrue (m3.getInches() == 4);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 0); 
        assertEquals (m.getInches(), 4); 
        assertEquals (m2.getFeet(), 0); 
        assertEquals (m2.getInches(), 0); 
    }
    
    public void testMinus() { // normal
    	Measurement m = new Measurement(7, 9);
    	Measurement m2 = new Measurement(5, 4);
    	Measurement m3 = m.minus(m2);
    	assertTrue (m3.getFeet() == 2);
        assertTrue (m3.getInches() == 5);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 7); 
        assertEquals (m.getInches(), 9); 
        assertEquals (m2.getFeet(), 5); 
        assertEquals (m2.getInches(), 4); 
    }
    
    public void testMinus2() { // convert 
    	Measurement m = new Measurement(7, 4);
    	Measurement m2 = new Measurement(5, 5);
    	Measurement m3 = m.minus(m2);
    	assertTrue (m3.getFeet() == 1);
        assertTrue (m3.getInches() == 11);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 7); 
        assertEquals (m.getInches(), 4); 
        assertEquals (m2.getFeet(), 5); 
        assertEquals (m2.getInches(), 5); 
    }
    
    public void testMinus3() { // even crazier
    	Measurement m = new Measurement(700, 4);
    	Measurement m2 = new Measurement(100, 100);
    	Measurement m3 = m.minus(m2);
    	assertTrue (m3.getFeet() == 592);
        assertTrue (m3.getInches() == 0);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 700); 
        assertEquals (m.getInches(), 4); 
        assertEquals (m2.getFeet(), 108); 
        assertEquals (m2.getInches(), 4); 
    }
    
    public void testMinus4() { // 0
    	Measurement m = new Measurement(0, 4);
    	Measurement m2 = new Measurement(0, 0);
    	Measurement m3 = m.minus(m2);
    	assertTrue (m3.getFeet() == 0);
        assertTrue (m3.getInches() == 4);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 0); 
        assertEquals (m.getInches(), 4); 
        assertEquals (m2.getFeet(), 0); 
        assertEquals (m2.getInches(), 0); 
    }
    
    public void testMultiple() { // normal
    	Measurement m = new Measurement(0, 7);
    	Measurement m2 = m.multiple(3);
    	assertTrue (m2.getFeet() == 1);
        assertTrue (m2.getInches() == 9);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 0); 
        assertEquals (m.getInches(), 7); 
    }
    
    public void testMultiple2() { // convert 
    	Measurement m = new Measurement(1, 7);
    	Measurement m2 = m.multiple(9);
    	assertTrue (m2.getFeet() == 14);
        assertTrue (m2.getInches() == 3);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 1); 
        assertEquals (m.getInches(), 7); 
    }
    
    public void testMultiple3() { // convert
    	Measurement m = new Measurement(1, 6);
    	Measurement m2 = m.multiple(2);
    	assertTrue (m2.getFeet() == 3);
        assertTrue (m2.getInches() == 0);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 1); 
        assertEquals (m.getInches(), 6); 
    }
    
    public void testMultiple4() { // 0
       	Measurement m = new Measurement(3, 0);
       	Measurement m2 = m.multiple(2);
       	assertTrue (m2.getFeet() == 6);
        assertTrue (m2.getInches() == 0);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 3); 
        assertEquals (m.getInches(), 0); 
    }
       
    public void testMultiple5() { // 0
       	Measurement m = new Measurement(3, 5);
       	Measurement m2 = m.multiple(0);
       	assertTrue (m2.getFeet() == 0);
        assertTrue (m2.getInches() == 0);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 3); 
        assertEquals (m.getInches(), 5); 
    }
       
    public void testMultiple6() { // identity 
       	Measurement m = new Measurement(3, 5);
       	Measurement m2 = m.multiple(1);
       	assertTrue (m2.getFeet() == 3);
        assertTrue (m2.getInches() == 5);
        // test old measurements are not modified
        assertEquals (m.getFeet(), 3); 
        assertEquals (m.getInches(), 5); 
    }
       
    public void testtoString() {  
        Measurement m = new Measurement(3, 5);
        assertEquals (m.toString(), "3'5\""); 
        System.out.println(m); 
    }
       
    public void testtoString2() {  
        Measurement m = new Measurement(0, 0);
        assertEquals (m.toString(), "0'0\""); 
        System.out.println(m); 
    }
       
    public void testtoString3() {  
        Measurement m = new Measurement(700, 900);
        assertEquals (m.toString(), "775'0\""); 
        System.out.println(m); 
    }
       
    

}
