public class ResizableIntSequence extends IntSequence {

    public ResizableIntSequence(int capacity) {
        super(capacity);
    }

    public void add(int toBeAdded) {
        if (myCount == maxLength) {expand();}
        myValues[myCount] = toBeAdded;
        myCount++;
    }

    public void insert(int toInsert, int insertPos) {
        if (myCount == maxLength) {expand();}
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    public void expand() {
        int[] oldValues = myValues;
        myValues = new int[maxLength+1];
        for (int elm = 0; elm < maxLength; elm++) {
            myValues[elm] = oldValues[elm];
        }
        maxLength++;
    }

    public int remove(int value){
        int returnInt = myValues[value];
        for(int x=value; x < maxLength-1; x++) {
            myValues[x] = myValues[x+1];
        }
        myCount--;
        int[] oldValues = myValues;
        myValues = new int[maxLength-1];
        for (int elm = 0; elm < myCount; elm++) {
            myValues[elm] = oldValues[elm];
        }
        maxLength--;
        return returnInt;
    }

}
