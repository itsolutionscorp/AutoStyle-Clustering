public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }

    // I also know the method using dayOfYear and utilizing the array monthLengths. I just wanted more coding practice. 
    public GregorianDate nextDate(){
    	int nextDay, nextMonth, nextYear;
    	if (month() == 1 || month() == 3 || month() == 5 || month() == 7|| month() == 8 || month() == 10) {
    		if (dayOfMonth() == 31) {
    			nextMonth = month() + 1;
    			nextDay = 1;
    			nextYear = year();
    			GregorianDate answer = new GregorianDate(nextYear, nextMonth, nextDay);
    			return answer;
    		} else {
    			nextDay = dayOfMonth() + 1;
    			nextMonth = month();
    			nextYear = year();
    			GregorianDate answer = new GregorianDate(nextYear, nextMonth, nextDay);
    			return answer;
    		}
    	} else if (month() == 4 || month() == 6 || month() == 9 || month() == 11) {
    		if (dayOfMonth() == 30) {
    			nextMonth = month() + 1;
    			nextDay = 1;
    			nextYear = year();
    			GregorianDate answer = new GregorianDate(nextYear, nextMonth, nextDay);
    			return answer;
    		} else {
    			nextDay = dayOfMonth() + 1;
    			nextMonth = month();
    			nextYear = year();
    			GregorianDate answer = new GregorianDate(nextYear, nextMonth, nextDay);
    			return answer;
    		}
    	} else if (month() == 2) { 
    		if (dayOfMonth() == 28) {
    			nextMonth = month() + 1;
    			nextDay = 1;
    			nextYear = year();
    			GregorianDate answer = new GregorianDate(nextYear, nextMonth, nextDay);
    			return answer;
    		} else {
    			nextDay = dayOfMonth() + 1;
    			nextMonth = month();
    			nextYear = year();
    			GregorianDate answer = new GregorianDate(nextYear, nextMonth, nextDay);
    			return answer;
    		}
    	} else {
    		if (dayOfMonth() == 31) {
    			nextMonth = 1;
    			nextDay = 1;
    			nextYear = year() + 1;
    			GregorianDate answer = new GregorianDate(nextYear, nextMonth, nextDay);
    			return answer;
    		} else {
    			nextDay = dayOfMonth() + 1;
    			nextMonth = month();
    			nextYear = year();
    			GregorianDate answer = new GregorianDate(nextYear, nextMonth, nextDay);
    			return answer;
    	} 
    		
    }
}

}