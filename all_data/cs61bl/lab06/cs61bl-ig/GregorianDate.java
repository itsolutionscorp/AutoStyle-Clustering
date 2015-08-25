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

	@Override
	public Date nextDate() {
		int year = year();
		int month = month();
		int day = dayOfMonth() + 1;
		if(monthLengths[month - 1] < day) {
			day -= monthLengths[month - 1];
			month++;
			if(month > monthLengths.length) { 
				month -= monthLengths.length;
				year++;
			}
		}
		return new GregorianDate(year, month, day);
		
	}

	public static void main(String[] args) {
		GregorianDate d = new GregorianDate(1, 12, 31);
		GregorianDate next = (GregorianDate) d.nextDate();
		System.out.println(next.toString());
	}
	

}
