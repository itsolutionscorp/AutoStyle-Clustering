import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListNode implements Serializable {
	private String myMsg;
	private int myID;
	private ListNode myPrev;
	private Date myDate;

	public ListNode(int id, ListNode prev, String msg) {
		myID = id;
		myPrev = prev;
		myMsg = msg;
		myDate = new Date();
	}

	public int getID() {
		return myID;
	}

	public ListNode getPrev() {
		return myPrev;
	}

	public String getMsg() {
		return myMsg;
	}

	public String getDate() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(myDate);
	}
}
