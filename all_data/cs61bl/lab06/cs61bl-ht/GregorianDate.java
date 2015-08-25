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
    public GregorianDate nextDate() {
        int day = dayOfMonth();
        int month = month();
        int year = year();

        if (day == monthLengths[month - 1]) {
            // If it's the last day of the last month of the year,
            // tomorrow is the first day of the first month of the next year.
            if (month == monthLengths.length)
                return new GregorianDate(year + 1, 1, 1);
            // If it's the last day of any other month,
            // tomorrow is the first day of the next month of the same year.
            return new GregorianDate(year, month + 1, 1);
        }
        // Otherwise, tomorrow is the next day of the same month of the same year.
        return new GregorianDate(year, month, day + 1);
    }

}
