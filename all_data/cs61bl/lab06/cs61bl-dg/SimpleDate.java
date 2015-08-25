import java.util.Arrays;

public class SimpleDate extends Date implements Comparable<SimpleDate> {

    public SimpleDate(int month, int day) {
        super(1, month, day);
    }

    public int compareTo(SimpleDate d) {
        return dayOfYear() - d.dayOfYear();
    }

    public Date nextDate() {
        if (dayOfMonth() == 30) {
            return new SimpleDate(month()+1, 1);
        }
        return new SimpleDate(month(), dayOfMonth()+1);
    }

    public int dayOfYear() {
        return month() * 30 + dayOfMonth();
    }

    public String toString() {
        return "" + month() + "/" + dayOfMonth();
    }


    public static void main (String [ ] args) {
        SimpleDate [ ] dArray = new SimpleDate [4];
        dArray[0] = new SimpleDate (5, 2); // 5/2
        dArray[1] = new SimpleDate (2, 9); // 2/9
        dArray[2] = new SimpleDate (6, 3); // 6/3
        dArray[3] = new SimpleDate (1, 11); // 1/11
        Arrays.sort (dArray);
        for (int k=0; k<dArray.length; k++) {
            System.out.println(dArray [k]);
        }
        // should print the dates in chronological order:
        // 1/11, 2/9, 5/2, 6/3
    }

}
