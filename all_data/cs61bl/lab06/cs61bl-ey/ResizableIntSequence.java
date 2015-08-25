
public class ResizableIntSequence extends IntSequence{

	public ResizableIntSequence(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void add(int toBeAdded){
		if (super.myCount >= super.myValues.length){
			int mCap[] = new int[super.myValues.length + 20];
			for (int i = 0; i < super.myValues.length; i++){
				mCap[i] = super.myValues[i];
			}
			super.myValues = mCap;
		}
		super.add(toBeAdded);		
	}
	
	@Override
	public void insert(int toInsert, int insertPos){
		if (super.myCount >= super.myValues.length){
			int mCap[] = new int[super.myValues.length + 20];
			for (int i = 0; i < super.myValues.length; i++){
				mCap[i] = super.myValues[i];
			}
			super.myValues = mCap;
		}
		super.insert(toInsert, insertPos);		
	}
	
	@Override
	public int remove(int pos){
		int x = super.remove(pos);
		if (super.myCount * 6 < myValues.length){
			int mCap[] = new int[super.myCount * 6];
			for (int i = 0; i < super.myCount; i++){
				mCap[i] = super.myValues[i];
			}
			super.myValues = mCap;
		}
		return x;
	}
	
}
