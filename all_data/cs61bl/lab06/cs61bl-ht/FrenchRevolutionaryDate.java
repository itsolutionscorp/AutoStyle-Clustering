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

    @Override
    public FrenchRevolutionaryDate nextDate() {
        int day = dayOfMonth();
        int month = month();
        int year = year();
        // If today is the last day of one of the first 12 months, then tomorrow is
        // The first day of the next month of the same year
        if (month < 13 && day == 30)
            return new FrenchRevolutionaryDate(year, month + 1, 1);
        // If today is the last day of the last month of the year, then tomorrow is
        // The first day of the first month of the next year
        if (month == 13 && day == 5)
            return new FrenchRevolutionaryDate(year + 1, 1, 1);
        // Otherwise, it is not the last day of any month, so tomorrow is
        // The next day of the same month of the same year
        return new FrenchRevolutionaryDate(year, month, day + 1);
    }

}
