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
        int lastdigit = id % 10;
        int otherdigits = id / 10;
        int otherdigitsum = 0;
        while (otherdigits != 0) {
            otherdigitsum += otherdigits % 10;
            otherdigits = otherdigits / 10;
        }
        int otherdigitsumlastvalue = otherdigitsum % 10;
        if (otherdigitsumlastvalue != lastdigit) {
            isLegal = false;
        }
        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
