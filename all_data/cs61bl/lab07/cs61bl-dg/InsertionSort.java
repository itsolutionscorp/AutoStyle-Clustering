public class InsertionSort {

    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
        if (k == 0) {
            return;
        }
        if (list[k] < list[k-1]) {
            int toinsert = list[k];
            for (int elm = k-1; elm >= 0; elm--) {
                list[elm+1] = list[elm];
                if (elm == 0 || list[elm-1] < toinsert) {
                    list[elm] = toinsert;
                    return;
                }
            }
        }
    }

    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {
        if (k < 0 || k > list.length) {
            throw new IllegalStateException("Invalid length to check.");
        }
        int highestelm = 0;
        for (int elm = 0; elm <= k; elm++) {
            if (highestelm > list[elm]) {
                throw new IllegalStateException("Elements not in order.");
            }
            highestelm = list[elm];
        }
    }

    public static int[] insertionSort(int[] list) {
        int[] rtn = new int[list.length];
        for (int k = 0; k < list.length; k++) {
            rtn[k] = list[k];
        }
        for (int k = 0; k < rtn.length; k++) {
            insert(rtn, k);
            try {
                isOK(rtn, k);
            } catch (IllegalStateException e) {
                System.err.println("inconsistency at position " + k);
            }
        }
        return rtn;
    }

    public static void main (String[] args) {
        int[] list = {3, 1, 7, 4, 5, 9, 2, 8, 6};
        list = insertionSort(list);
        for (int k = 0; k < list.length; k++) {
            System.out.print(list[k]);
        }
        System.out.println();
    }

}
