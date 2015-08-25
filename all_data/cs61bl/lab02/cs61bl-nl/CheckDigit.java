public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        try {
            id = Integer.parseInt (args[0]);
        } 
        
        catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }

        boolean isLegal = true;
        // TODO Your code here
        int counter=0;
        int sum=0;
        int lastDigitOfID=0;
        int tempID=id;
        
        while(counter<9){
        	int digit= tempID%10;       	
        	tempID=tempID/10;
        	
        	if(counter==0){
        		lastDigitOfID=digit;
        	}
        	
        	else
        	{
        		sum+=digit;
        	}
        	
        	counter++;
        }
        
        
        int lastDigit=sum%10;
        if(lastDigitOfID==lastDigit){
        	isLegal=true;
        }else{
        	isLegal=false;
        }
        
        if (isLegal) {
            System.out.println (id + " is legal");
        } 
        
        else {
            System.out.println (id + " is not legal");
        }
    }

}
