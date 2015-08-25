import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class InstanceVariable implements Serializable{
    public ListNode myHeader; //reference variable to the header of a branch
    public String myCurrentBranch;
    public int myGlobalID = 0; //used to issue unique id to each commmit
    public HashMap<String, ListNode> myBranchHeader; //keeps track of what the header for each branch is
    public HashMap<String, Integer> myTrackFiles;  //!! 
    public HashSet<String> myStageList;
    public ArrayList<ListNode> myLogList; //Stores the commits made
    public HashMap<String, Integer> myUntrackList;
    public boolean isConflicted;
}
