public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }
    public Date nextDate(){
    	int a = year();
    	int b = month();
    	int c = dayOfMonth();
    		if (c + 1 > monthLengths[b-1]) {
    			c = 1;
    			if (b+1 > monthLengths.length) {
    				b = 1;
    				a ++;
    			}
    			else {
    				b++;
    			}
    		}
    		else {
    			c ++;
    		}
   
    		
    			
//            if (b == 2) {
//                if (c == 28) {
//                	b = 3;
//                	c = 1;
//                
//                }
//                else {
//                	c ++;
//                }
//                
//               }
//            else if (b == 4 || b == 6 || b == 9 || b == 11) {
//            	if (c == 30) {
//            		c = 1;
//            		b ++;
//            		
//            	}
//            	else {
//                	c ++;
//                }
//            	
//            }
//            else {//if (b == 12 || b == 1 || b == 3 || b == 5 || b == 7 || b == 8 || b == 10) {
//                if (b==12 && c == 31) {
//                	b = 1;
//                	c = 1;
//                	a ++;
//                	
//                }
//                else {
//                	if(c == 31) {
//                	b ++;
//                	c = 1;
//                	
//                }
//                	else {
//                    	c ++;
//                    }
//                	 }
//            }
            
            GregorianDate date = new GregorianDate(a, b, c);
			return date;
          
        }
}
