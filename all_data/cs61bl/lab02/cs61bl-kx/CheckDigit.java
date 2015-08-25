public class CheckDigit {

    public static void main (String[] args) {
        int id = 0;
        try {
            id = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }

        boolean isLegal = false;
        int check = id % 10;
        int front = id / 10;
        int total = 0;
        while (front > 0) {
            total += (front % 10);
            front /= 10;
        }

        if (total % 10 == check) {
            isLegal = true;
        }

        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
