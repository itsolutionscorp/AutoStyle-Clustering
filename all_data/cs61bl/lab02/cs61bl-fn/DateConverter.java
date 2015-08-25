
public class DateConverter {

    // Given a day number in 2008, an integer between 1 and 366,
    // as a command-line argument, prints the date in month/day format.
    // Example: java DateConverter 365
    // should print 12/30

    // The code is missing two assignment statements.
    public static void main (String[] args) {

        int dayOfYear = 0;
        try {
            dayOfYear = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        int month, dateInMonth, daysInMonth;
        month = 1;
        daysInMonth = 31;

        while (dayOfYear > daysInMonth) {
            
            if (month == 2) {
                daysInMonth = 29;
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                daysInMonth = 30;
            } else {
                daysInMonth = 31;
            }
            dayOfYear = dayOfYear - daysInMonth;
            month++;
            // *** Here is another possible place to put assignment statements.
        }

        dateInMonth = dayOfYear;
        System.out.println (month + "/" + dateInMonth);
    }

}
