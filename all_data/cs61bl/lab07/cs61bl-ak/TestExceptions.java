

public class TestExceptions {
    public static void main (String [ ] args) {
        int[] a = null;
        Object[] r = new String[3];
        Object y = new String("");
        Object c = new Object();
        c = "c";
        try {
            int b = a.length;
        } catch (NullPointerException e) {
            System.out.println ("got null pointer");
        }
        try {
            r[0] = new int[4];
        } catch (ArrayStoreException e) {
            System.out.println ("got illegal array store");
        }
        try {
        	int[] arr = (int[]) c;
        } catch (ClassCastException e) {
            System.out.println ("got illegal class cast");
        }
    }
}