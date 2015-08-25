import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Gitlet{
	
	public static void main(String[] args) {
				
		CommitList gitletCommits = null;
		
		
		String input;
		
        String fileName = "gitletCommitsObject.ser";
        File myGitletFile = new File(".gitlet/" + fileName);
        
        
        //Deserialization
        if(!myGitletFile.exists()){
    		gitletCommits = new CommitList();
        } else{
//        	System.out.println("deserializing yeeeee");
        	try {
                FileInputStream fileIn = new FileInputStream(myGitletFile);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                gitletCommits = (CommitList)objectIn.readObject();
                objectIn.close();
            } catch (IOException e) {
            	System.out.println("IOException while deserializing str"); //Got this error here
            	e.printStackTrace();
            } catch (ClassNotFoundException e) {
            	System.out.println("ClassNotFoundException while deserializing str");
            	e.printStackTrace();
            }
	       
        }
	
		
		
		if(args.length == 0){
			System.out.println("Please enter a command.");
			System.exit(0);
		}
		
		String command = args[0];

//		System.out.println("command: " + command);
		
		switch ( command ){
			case "init":
				try{
				gitletCommits.init();
				} catch(IOException e){
					System.out.println("Fucked up");
				}
				break;
				
			case "add":
				if(args.length < 2){
					System.out.println("missing argument");
					return;
				}
				input = args[1];
				try{
					gitletCommits.add(input);
				} catch(IOException e){
					System.out.println("Fucked up");
				}
				break;
			
			case "commit":
				if(args.length < 2){
					System.out.println("missing argument");
					System.exit(0);
				}
				int singleQoute1 = args[1].indexOf("'");
				int singleQoute2 = args[1].indexOf("'",singleQoute1+1);
				if(singleQoute1 != -1 && singleQoute2 != -1){
					input = args[1].substring(singleQoute1+1, singleQoute2);
				} else{
					input = args[1];
				}
				input = args[1];
				try{
					gitletCommits.commit(input, false);
				} catch(IOException e){
					System.out.println("Fucked up");
				}
				break;
				
			case "rm":
				if(args.length < 2){
					System.out.println("missing argument");
					System.exit(0);
				}
				input = args[1];
				gitletCommits.remove(input);
				break;
				
			case "log":
				gitletCommits.log();
				break;
				
			case "global-log":
				gitletCommits.globalLog();
				break;
				
			case "status":
				gitletCommits.status();
				break;
				
			case "checkout":
				try{
					if(args.length == 2){
						HashMap<String,CommitNode> branchMap = gitletCommits.getBranches();
						if(branchMap.containsKey(args[1])){
							gitletCommits.checkout_branch(args[1]);
						} else {
							gitletCommits.checkout_filename(args[1]);
						}
					} else if(args.length == 3){
						gitletCommits.checkout_ID(Integer.parseInt(args[1]), args[2]);
					} else {
						System.out.println("invalid arguments");
						System.exit(0);
					}
				} catch( IOException e){
					System.out.println("checkout IO exception");
					System.out.println("SHITFUCKER");
					e.printStackTrace();
				}
				break;
				
			case "branch":
				if(args.length < 2){
					System.out.println("missing argument");
					return;
				}
				gitletCommits.branch(args[1]);
				break;
				
			case "find":
				if(args.length < 2){
					System.out.println("missing argument");
					return;
				}
				gitletCommits.find(args[1]);
				break;
			
			case "rm-branch":
				if(args.length < 2){
					System.out.println("missing argument");
					return;
				}
				gitletCommits.rm_branch(args[1]);
				break;
				
			case "reset":
				if(args.length < 2){
					System.out.println("missing argument");
					return;
				}
				try{
					gitletCommits.reset(Integer.parseInt(args[1]));
				} catch(IOException e){
					System.out.println("IOException");
				}
				break;
				
			case "merge":
				if(args.length < 2){
					System.out.println("missing argument");
				}
				try{
					gitletCommits.merge(args[1]);
				} catch(IOException e){
					System.out.println("IO Exception");
				}
				break;
			
			case "rebase":
				if(args.length < 2){
					System.out.println("missing argument");
				}
				try{
					gitletCommits.rebase(args[1]);
				} catch(IOException e){
					System.out.println("IO Exception");
				}
				break;
			
			
			default:
				System.out.println("No command with that name exists.");
				System.exit(0);
				break;
				

				
		}
		

		
		try {
	        File myCommitFile = new File(".gitlet/" + fileName);
	        FileOutputStream fileOut = new FileOutputStream(myCommitFile);
	        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	        objectOut.writeObject(gitletCommits);
	        objectOut.close();
	     } catch (IOException e) {
	    	 System.out.println("Serialization error");
	    	 e.printStackTrace();
	     }
		
	}
	
	
}