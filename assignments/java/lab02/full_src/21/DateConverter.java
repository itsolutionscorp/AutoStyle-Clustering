
public class DateConverter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int dayOfYear = 0;
        try {
            dayOfYear = Integer.parseInt (args[0]);
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int month, dateInMonth, daysInMonth;
        daysInMonth = 31;
        month = 1;
        while (dayOfYear > daysInMonth) {
            dayOfYear = dayOfYear - daysInMonth;
            month ++;
            if (month == 2) {
                daysInMonth = 29;
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                daysInMonth = 30;
            } else {
                daysInMonth = 31;
            }
        }
        dateInMonth = dayOfYear;
        System.out.println (month + "/" + dateInMonth);
	}

}

