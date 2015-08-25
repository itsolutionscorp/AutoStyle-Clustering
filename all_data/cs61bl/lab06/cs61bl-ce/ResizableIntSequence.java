public class ResizableIntSequence extends IntSequence {



public ResizableIntSequence(int capacity) {

super(capacity);

}


public void add(int toBeAdded) {

        // YOUR CODE HERE
	int[] newValues;

    if (myCount == myValues.length) {

    newValues = new int [2 * myValues.length];

    for (int k = 0; k < myCount; k++) {

    newValues[k] = this.elementAt(k);

    } 

    myValues = newValues;

    }

    myValues[myCount] = toBeAdded;

    myCount++;

    }

 

public void insert (int toInsert, int pos) {

        //This is the non-buggy version of insert.

int[] newValues;

if (myCount == myValues.length) {

    newValues = new int [2 * myValues.length];

    for (int k = 0; k < myCount; k++) {

    newValues[k] = this.elementAt(k);

    } 

    myValues = newValues;

}

myCount++;

    if (pos < 0 || pos > myCount) {

    return;

    } else {

    int j = myValues.length - 1;

    for (; j > 0; j--){

    if (j > pos){

    this.myValues[j] = this.myValues[j-1];

    } else if (j == pos){

    myValues[j] = toInsert;

    }}

    if(pos == 0 && j==0)

{myValues[0] = toInsert;}

    }

    }

 

  public void remove(int pos) {

  int[] newValues;

    if (pos < 0 || pos > myValues.length) {

    return;

    } else

    for (int i = 0; i < myValues.length - 1; i++){

    if (i < pos) {

    continue;

    } else {

    myValues[i]= myValues[i+1];

    }

    myCount--;

    }

    if (myCount < myValues.length/2) {

        newValues = new int [myValues.length -((myValues.length - myCount)/2)];

        for (int k = 0; k < myCount; k++) {

        newValues[k] = this.elementAt(k);

        } 

        myValues = newValues;

    }

    }	   

}