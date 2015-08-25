public class ResizableIntSequence extends IntSequence{
	public ResizableIntSequence(int capacity){
		super(capacity);
	}
	public void add(int toBeAdded){
		if(size()+1>myValues.length){
			int[] temp = new int[myValues.length+10];
			for(int i=0;i<size();i++){
				temp[i] = myValues[i];
				
			}
			myValues = temp;
			
		}
		super.add(toBeAdded);
		
	}
	public void insert(int toInsert, int pos){
		if(pos<0 || pos>size()){
			System.out.println("invalid position");
			System.exit(1);
		}
		if(size()+1>myValues.length){
			int[] temp = new int[myValues.length+10];
			for(int i=0;i<size();i++){
				temp[i] = myValues[i];
				
			}
			myValues = temp;
			
		}
		super.insert(toInsert, pos);
		
		
	}
	public void remove(int pos){
		if(pos<0 || pos >= myCount || size()==0){
			System.out.println("invalid position or already empty");
			System.exit(1);
		}
		super.remove(pos);
		if(size()<=myValues.length*0.5){
			int[] temp = new int[size() + (int)(size()*0.5)];
			for(int i=0;i<size();i++){
				temp[i] = myValues[i];
				
			}
			myValues = temp;
			
		}
		
	}
}