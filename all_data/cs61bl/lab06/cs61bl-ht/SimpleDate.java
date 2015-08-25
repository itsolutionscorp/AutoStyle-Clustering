import java.util.Arrays;

/**
 * A Date with no year and "infinite" months of 30 days
 * (or at least as many months as fit in an int. Several hundred thousand Gregorian years in the future,
 * people can make up end-of-the-world conspiracy theories because the SimpleDate calendar is about to end.)
 */
public class SimpleDate extends Date implements Comparable<SimpleDate> {

    public SimpleDate(int month, int day) {
        super(0, month, day);
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }

    @Override
    public Date nextDate() {
        if (dayOfMonth() == 30)
            return new SimpleDate(month() + 1, 0);
        return new SimpleDate(month(), dayOfMonth() + 1);
    }

    @Override
    public int compareTo(SimpleDate other) {
        if (month() == other.month())
            return Integer.compare(dayOfMonth(), other.dayOfMonth());
        return Integer.compare(month(), other.month());
    }

    @Override
    public String toString() {
        return String.format("%d/%d", month(), dayOfMonth());
    }

    public static void main(String[] args) {
        SimpleDate [ ] dArray = new SimpleDate [4];
        dArray[0] = new SimpleDate (5, 2); // 5/2
        dArray[1] = new SimpleDate (2, 9); // 2/9
        dArray[2] = new SimpleDate (6, 3); // 6/3
        dArray[3] = new SimpleDate (1, 11); // 1/11
        Arrays.sort(dArray);
        for (int k=0; k<dArray.length; k++) {
            System.out.println(dArray [k]);
        }
        // should print the dates in chronological order:
        // 1/11, 2/9, 5/2, 6/3
    }
}
