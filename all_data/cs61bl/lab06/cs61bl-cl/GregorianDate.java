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
	public Date nextDay() {
		// TODO Implement this method so it returns the date after this
		
		GregorianDate ret_date;
		ret_date = new GregorianDate(this.year(),this.month(),this.dayOfMonth()+1);
		return ret_date;
	}

}
