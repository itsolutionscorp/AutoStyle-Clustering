package lab06;
import java.util.Arrays;
import java.awt.Point;

public class SimpleDate implements Comparable<SimpleDate>{

		private int month;
		private int day;

		public SimpleDate(int month, int day){
			this.month = month;
			this.day= day;
		}
		
		public int compareTo(SimpleDate sd_object){
			// compare months
			if(sd_object.month > this.month){
				return -1;}
			else if((sd_object.month == this.month) && (sd_object.day> this.day)){
				return -1;
			}
			else{
				return 1;
			}
			
		}
		public String toString ( ){
			return this.month + "/" + this.day;
		}
//}
//	Point[] points
//	if (point1.getX > point2.getX){
//	    return 1;
//	} else if(point1.getX==point2.getX && point1.getY> point2.getY){
//		return true;
//} else 
//	return false;
//}
//
//	return Integer.compare(date1, date2);
//	}
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
