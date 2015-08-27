package lab02;

public class precheckdigit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=3465;
		int n=2;
	
		while(n>0){
			a=a/10;
			n=n-1;
		}
		System.out.print(a%10);

	}

}
