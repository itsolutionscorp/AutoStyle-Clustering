public class UnstableSelectionSort {
     
    public static void main (String [] args){
        /*Person [] personArray = new Person[8];
        personArray[0] = new Person(21, "Shan");
        personArray[1] = new Person(21, "Bob");
        personArray[2] = new Person(21, "Kat");
        personArray[3] = new Person(11, "Tom");
        personArray[4] = new Person(11, "Bill");
        personArray[5] = new Person(21, "Russell");
        personArray[6] = new Person(21, "David");
        personArray[7] = new Person(11, "Kyle");
         
         
        selectionSort(personArray);
        for(int i = 0; i < personArray.length; i++){
            System.out.println(personArray[i].age() + " " + personArray[i].name() + ", ");
        }*/
    }
 
    public static void selectionSort(Comparable[] arr) {
        System.out.println(arr.length);
        for (int j = 0; j < arr.length; j++) {
            int latestPos = 0;
            int insertPos = 0;
            for (int k = 0; k <= j; k++) {
                if (arr[latestPos].compareTo(arr[k]) == -1) {
                    latestPos = k;
                }
            }System.out.println(((Person)arr[latestPos]).name() + " " + ((Person)arr[j]).name());
            if (j != latestPos && arr[latestPos].compareTo(arr[j])!=0) {
                Comparable temp = arr[j];
                //arr[j] = arr[latestPos];
                for(int y = j; y > latestPos; y--){
                    arr[y] = arr[y-1];
                     
                }
                arr[latestPos] = temp;
            }
        }
    }
}