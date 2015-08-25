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

    

    public GregorianDate nextDate() {

    int nextDayofMonth = this.dayOfMonth() + 1;

    int nextYear = this.year();

    int nextMonth = this.month();

    if (nextDayofMonth > monthLengths[this.month() - 1]) {

    if (nextMonth==12) {

    nextYear++;

    nextMonth = 1;

    } else

    nextMonth++;

    nextDayofMonth = nextDayofMonth - monthLengths[this.month() - 1];

    }

    GregorianDate newDate = new GregorianDate(nextYear, nextMonth, nextDayofMonth);

    return newDate;

    }

    }