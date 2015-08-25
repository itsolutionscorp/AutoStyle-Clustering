import junit.framework.TestCase;


public class DateTest extends TestCase {
       public void testNextDate(){
    	   FrenchRevolutionaryDate ftest= new FrenchRevolutionaryDate(1,13,5);
    	   Date advance = ftest.nextDate();
    	   assertEquals(ftest.toString(),"5/13/1" );
    	   assertEquals(advance.toString(),"1/1/2");
    	   FrenchRevolutionaryDate reglast = new FrenchRevolutionaryDate(2015,12,30);
    	   Date advance1 = reglast.nextDate();
    	   assertEquals(reglast.toString(),"30/12/2015");
    	   assertEquals(advance1.toString(),"1/13/2015");
    	   FrenchRevolutionaryDate regmid = new FrenchRevolutionaryDate(1111,11,11);
    	   Date notsingle= regmid.nextDate();
    	   assertEquals(regmid.toString(),"11/11/1111");
    	   assertEquals(notsingle.toString(),"12/11/1111");
    	   FrenchRevolutionaryDate lastmreg = new FrenchRevolutionaryDate(1111,13,4);
    	   Date move= lastmreg.nextDate();
    	   assertEquals(lastmreg.toString(),"4/13/1111");
    	   assertEquals(move.toString(),"5/13/1111");
    	   GregorianDate febLast = new GregorianDate(1995,2,28);
    	   Date act = febLast.nextDate();
    	   assertEquals(febLast.toString(),"28/2/1995");
    	   assertEquals(act.toString(),"1/3/1995");
    	   GregorianDate birthday = new GregorianDate(1995,2,6);
    	   assertEquals(birthday.nextDate().toString(), "7/2/1995");
    	   assertEquals(birthday.toString(),"6/2/1995");
    	   GregorianDate OctLast = new GregorianDate(1994,10,31);
    	   assertEquals(OctLast.nextDate().toString(),"1/11/1994");
    	   assertEquals(OctLast.toString(),"31/10/1994");
    	   GregorianDate RyanBir = new GregorianDate(1994,10,17);
    	   assertEquals(RyanBir.nextDate().toString(),"18/10/1994");
    	   assertEquals(RyanBir.toString(),"17/10/1994");
    	   GregorianDate Dec = new GregorianDate(2015,12,24);
    	   assertEquals(Dec.nextDate().toString(),"25/12/2015");
    	   assertEquals(Dec.toString(),"24/12/2015");
    	   GregorianDate LastD = new GregorianDate(2015,12,31);
    	   assertEquals(LastD.nextDate().toString(), "1/1/2016");
    	   assertEquals(LastD.toString(),"31/12/2015");
    	   
    	   
       }
}
