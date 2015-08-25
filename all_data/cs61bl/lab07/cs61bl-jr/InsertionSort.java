public class InsertionSort {

    

    // Precondition: elements 0 through k-1 of list are in increasing order.

    // Postcondition: elements 0 through k of list are in increasing order.

    public static void insert (int list[], int k) {

    int[] newlist = new int[k + 1];

        boolean swapped = false;

        for (int count = 0; count <= k; count += 1) {

        if (swapped) { 

        newlist[count] = list[count - 1];

        } else if (list[count] <= list[k]) {

        newlist[count] = list[count];

        } else {

        newlist[count] = list[k];

        swapped = true;

        }

        for (int current = 0; current <= k; current++); {

        list[count] = newlist[count];

        }

        }



    }

    

    // Does nothing when the first k elements of LIST are sorted in

    // increasing order.

    // Throws an IllegalStateException otherwise.

    public static void isOK (int[] list, int k) {



        for (int current = 1; current <= k; current ++) {

        if (list[current] < list[current - 1]) {

        throw new IllegalStateException("Wrong order");

        }

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