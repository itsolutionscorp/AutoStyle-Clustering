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
        int idTruncated = id / 10;
        int sum = 0;
        while (idTruncated > 0){
            int remainder = idTruncated % 10;
            idTruncated = idTruncated / 10;
            sum = sum + remainder;
        }
        isLegal = sum % 10 == id % 10;

        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
