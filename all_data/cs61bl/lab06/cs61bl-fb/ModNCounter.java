import java.util.*;
public class ModNCounter extends Counter{

	public int MyN;
	public ModNCounter(int N){
        
		MyN = N;
		
				
	}
	@Override
	public int value(){
		return super.value()%MyN;
		
	}
 	
	public static void main(String[] args){
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
		modCounter.reset();
		modCounter.increment();
		System.out.println(modCounter.value()); // still prints 1
		
		
		
	}
	
}
