public class FrenchRevolutionaryDate extends Date {

	// In a nonleap year in the French Revolutionary Calendar,
	// the first twelve months have 30 days and month 13 has five days.
	public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
		super(year, month, dayOfMonth);
	}
	
	
	@Override
	public int dayOfYear() {
		return (month() - 1) * 30 + dayOfMonth();
	}

	public FrenchRevolutionaryDate nextDate(){
		if (this.helper(this.year()) && this.dayOfMonth()==5 &&this.month()==13 ){
			return new FrenchRevolutionaryDate(this.year(), this.month(),6);	
		}else if (this.helper(this.year()) && this.dayOfMonth()==6 &&this.month()==13 ){
			return new FrenchRevolutionaryDate(this.year()+1, 1,1);
		}else if(this.month()<13 && this.dayOfMonth() == 30){
			return new FrenchRevolutionaryDate(this.year(), this.month()+1,1);
		}else if(this.helper(this.year()) == false && this.dayOfMonth() == 5 && this.month() == 13) {
			return new FrenchRevolutionaryDate(this.year() + 1, 1, 1);
		}else{
			return new FrenchRevolutionaryDate(this.year(), this.month(),this.dayOfMonth()+1);	
		}
	}
	
	public static void main (String[] args){
		FrenchRevolutionaryDate french = new FrenchRevolutionaryDate(2014, 13, 5);
		System.out.println(french.nextDate().year());
		System.out.println(french.nextDate().dayOfMonth());
	}
}
