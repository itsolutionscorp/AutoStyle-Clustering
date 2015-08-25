public class FrenchRevolutionaryDate extends Date {



    // In a nonleap year in the French Revolutionary Calendar,

    // the first twelve months have 30 days and month 13 has five days.

    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {

        super(year, month, dayOfMonth);

    }



    @Override

    public int dayOfYear() {

        return (month() - 1) * 30 + dayOfMonth();

    }

    

    public Date nextDate() {

    int nextDayofMonth = this.dayOfMonth() + 1;

    int nextYear = this.year();

    int nextMonth = this.month();

    if (nextMonth <= 12) {

    if (nextDayofMonth > 30) {

    nextDayofMonth = 1;

    nextMonth++;

    }

    } else if (nextMonth == 13) {

    if (nextDayofMonth > 5) {

    nextDayofMonth = 1;

    nextMonth = 1;

    nextYear++;

    }

    }

    FrenchRevolutionaryDate newDate = new FrenchRevolutionaryDate(nextYear, nextMonth, nextDayofMonth);

    return newDate;

    }



}