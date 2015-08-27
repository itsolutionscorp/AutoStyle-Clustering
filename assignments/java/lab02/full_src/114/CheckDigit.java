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
        int remainingDigits = id; // start with the whole id
        int counter = 1; // part of keeping track of rightmost digit
        int digitSum = 0;
        int rightmostDigit = 0;
        
        // idea behind the while loop is to go through each digit
        while (remainingDigits > 0) {
            int currentDigit = remainingDigits % 10; // took off one digit
            remainingDigits = remainingDigits / 10; // update remainingDigits
            if (counter == 1) {
                rightmostDigit = currentDigit;
                counter = counter + 1;
                // will make sure that we do not go back to this condition
                // only want to get the rightmost digit
            }
            else {
                digitSum = digitSum + currentDigit;
                // adding digits
            }
        }
        int targetDigit = digitSum % 10;
        if (targetDigit != rightmostDigit) {
            isLegal = false;
        // check to see if the last digit is equal to the rightmost digit
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }
}