public class CheckDigit {

	public static void main(String[] args) {
		int id = 0;
		try {
			id = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println("The argument has to be a sequence of digits.");
			System.exit(1);
		}

		int lastDigit = id % 10;
		int remainder;
		int idProduct = id;
		int sum = 0;

		while (idProduct > 0) {
			idProduct = idProduct / 10;
			remainder = idProduct % 10;
			sum = sum + remainder;
		}
	

		boolean isLegal;

		if (sum % 10 == lastDigit) {
			isLegal = true;
		} else {
			isLegal = false;
		}

		// TODO Your code here

		if (isLegal) {
			System.out.println(id + " is legal");
		} else {
			System.out.println(id + " is not legal");
		}
	}

}
