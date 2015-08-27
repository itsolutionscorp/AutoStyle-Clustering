public class Picture {
    public static void main(String[] args) {
        try {
            int reps = Integer.parseInt(args[0]);
            while (reps > 0) {
                System.out.println("Looping!");
                reps--;
            }
        } catch (NumberFormatException e) {
            return;
        }
    }
}

