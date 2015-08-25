import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Version implements Serializable{
//parameters        
        private static final long serialVersionUID = 3l;            
	    private String time;
        private Version myParent;
        private boolean isBranchHead;
        private int ID;
        private String message;
        private String branchName;
        private HashMap<String, String> myMap;;
        private ArrayList<String> tracked;

        public Version (int id, String messageVar,Version parent, boolean isBranchHead, String branchName) {
            ID=id;
            this.branchName = branchName;
            myParent = parent;
            message = messageVar;
            this.isBranchHead = isBranchHead;
            SimpleDateFormat time_now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time = time_now.format(new Date());
            tracked = new ArrayList<String>();
            myMap =new HashMap<String, String>();
        }
        public HashMap<String, String>  file() {
            return myMap;
        }
        public void myMapPut(String fileName, String path) {
            myMap.put(fileName, path);
        }
        
        @Override
        public int hashCode() {
            return ID;
        }
        public Version getParent() {
            return myParent;
        }
        public void setParent(Version parent) {
        	myParent=parent;
        }
        public int getID() {
            return ID;
        }
        public boolean isBranchHead() {
            return isBranchHead;
        }
        public String time(){
            return time;
        }
        public String message(){
            return message;
        }
        public String branchName(){
            return branchName;
        }
        public void log(){
            System.out.println("===");
            System.out.println(this.hashCode());  // System.out.println(“Commit” + this.hashCode());
            System.out.println(this.time());
            System.out.println(this.message()+ "\n");
        }
        
        public void addtrakced(String name) {
                tracked.add(name);
        }
        
        public void removetracked(String name){
                tracked.remove(name);
        }
        
        public ArrayList<String> gettracked() {
            return tracked;
        }

      
}
