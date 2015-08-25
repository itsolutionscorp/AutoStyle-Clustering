public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        try {
            id = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }

        boolean isLegal = true;
        int temp=id;
        int sumId=0;
        int lastdigit;
        int storedigit;
        lastdigit=temp%10;
        temp=(temp-lastdigit)/10;
        storedigit=temp%10;
        while (temp>0){
        	sumId=sumId+storedigit;
        	temp=(temp-storedigit)/10;
        	storedigit=temp%10;
        }
        storedigit=sumId%10;
        if (storedigit!=lastdigit){
        	isLegal=false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
