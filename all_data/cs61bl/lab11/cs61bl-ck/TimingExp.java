import java.util.ArrayList;
import java.util.LinkedList;


public class TimingExp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timer timer = new Timer();
		ArrayList<Integer> test = new ArrayList<Integer>();
		timer.start();
		for (int i = 0; i < 100000; i++) {
			test.add((int) Math.random());
		}
		timer.stop();
		System.out.println("Time for Arraylist to add : " + timer.elapsed());
		
		
		
		timer.reset();
		LinkedList list = new LinkedList();
		timer.start();
		for (int i = 0; i < 100000; i++) {
			list.add((int) Math.random());
		}
		timer.stop();
		System.out.println("Time for LinkedList to add : " + timer.elapsed());
		
		
		timer.reset();
		LinkedList list2 = new LinkedList();
		for (int i = 0; i < 100000; i ++) {
			list2.add((int) Math.random());
		}
		timer.start();
		list2.get(99999);
		timer.stop();
		System.out.println("Time for doubly linkedlist to get the last elements : " + timer.elapsed());
		
		
		timer.reset();
		List list3 = new List();
		for (int i = 0; i < 100000; i ++) {
			list3.add((int) Math.random());
		}
		timer.start();
		list3.get(99999);
		timer.stop();
		System.out.println("Time for doubly List to get the last elements : " + timer.elapsed());
	}

}
