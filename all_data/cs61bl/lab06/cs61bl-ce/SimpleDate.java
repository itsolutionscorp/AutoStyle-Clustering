import java.util.Arrays;



public class SimpleDate implements Comparable<SimpleDate> {



public int myMonth;

public int myDay;



public SimpleDate(int month, int day) {

this.myMonth = month;

this.myDay = day;

}


public int compareTo(SimpleDate other) {

if (this.myMonth==other.myMonth) {

return Integer.compare(this.myDay, other.myDay);

} else 

return Integer.compare(this.myMonth, other.myMonth);

}


public String toString() {

        return  "" + myMonth + "/" + myDay;

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