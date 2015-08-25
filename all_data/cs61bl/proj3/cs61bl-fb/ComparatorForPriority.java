import java.util.Comparator;


public class ComparatorForPriority implements Comparator<Status> {

	@Override
	public int compare(Status arg0, Status arg1) {
		// TODO Auto-generated method stub
		if (arg0.Cost < arg1.Cost) {
			return -1;
		} else if (arg0.Cost > arg1.Cost) {
			return 1;
		} else {
			return 0;
		}
	}

}
