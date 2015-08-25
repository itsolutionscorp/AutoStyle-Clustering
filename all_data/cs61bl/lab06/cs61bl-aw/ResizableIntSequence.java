public class ResizableIntSequence extends IntSequence {


        public ResizableIntSequence(int capacity) {
                super(capacity);
        }


        //Override the add and insert methods so that whenever either of these methods is called on a full ResizableIntSequence,
        //the ResizableIntSequence increases its capacity to accomodate for the new elements.
        @Override
    public void add(int toBeAdded) {
            // when there are not enough space to add the value
        //store the old ray
            int[] temp = new int[super.size()];
            for (int i = 0; i< super.size(); i++){
                    temp[i] = super.myValues[i];
            }
                if (super.myCount == super.myValues.length) {
                        super.myValues = new int [(super.size()+1)];
                        // put the old value inside the new myValues;
                        for (int i =0; i <myCount; i++){
                                super.myValues[i] = temp[i];
                        }
super.myCount++;
                        super.myValues[super.myCount]= toBeAdded;
                        
            }else{
            insert(toBeAdded, myCount);
            }
        }
        
        
    public void insert(int newInt, int pos){
            // when newInt is larger than myCount than change the size as newInt and then make the value between the old myCount to newInt to be zero
            int[] temp = new int[super.size()];
            for (int i = 0; i< super.size(); i++){
                    temp[i] = super.myValues[i];
            }
            
             if(pos >= super.myValues.length){
                        super.myValues = new int[pos+1];
                        for (int i = 0;i <super.myCount; i++){
                                super.myValues[i] = temp[i];
                        }
                        super.myValues[pos]= newInt;
                        super.myCount = pos+1;
                }
                else if(super.myValues.length == super.myCount){
                        super.myValues = new int[super.myCount+1];
                        for (int i = 0;i <super.myCount; i++){
                                super.myValues[i] = temp[i];
                        }
                        super.insert(newInt, pos);
                
                        
                }
                else{
                        super.insert(newInt, pos);
                }


    }


// the remove method, from my understanding, make the array has the same length of my count
 public int remove(int pos){
	 super.remove(pos);
        // store my old array
	 int[] temp = new int[super.size()];
            for (int i = 0; i< super.size(); i++){
                    temp[i] = super.myValues[i];
            }
        super.myValues = new int[super.size() - 1];
                //put the value into
                for (int i = 0; i <myCount; i++){
                super.myValues[i] = temp[i];
        }
          return pos; 


}
 
}