
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;


public class Commit implements Serializable{
	//public static int idValue = 0;
	private Integer id;
	private String comment;
	HashMap<String, File> snapShot = new HashMap<String, File>(30, .7F );
	//or should this just be one parent file (can access all parents through
	//the abstract pathname and the File class methods
	private SimpleDateFormat dateFormat;
	private Date date;
	private int parentID = -1;
	private String branchName;

	public Commit(String comment){
		this.id = 0;
		//Commit.idValue++;
		this.comment = comment;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		date = new Date();
		this.branchName = "";
	}

	public Commit(String comment, String branchName){
		this.id = 0;
		//Commit.idValue++;
		this.comment = comment;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		date = new Date();
		this.branchName = branchName;
	}

	public String getDate() {
		return dateFormat.format(date);
	}

	public int getID(){
		return this.id;
	}
	public void setID(int input){
		this.id = input;
	}
	public String getComment(){
		return this.comment;
	}
	public void setParentID(Commit parent){
		this.parentID = parent.getID();
	}
	public int getParentID(){
		return this.parentID;
	}

	public String getBranchName(){
		return this.branchName;
	}
	public void setBranchName(String branchName){
		this.branchName = branchName;
	}
	@Override
	public int hashCode(){
		return id.hashCode();
	}
	@Override
	//don't know if this is right
	public boolean equals(Object o){
		if (this.toString().equals(o.toString())){
			return true;
		}
		return false;
	}
}