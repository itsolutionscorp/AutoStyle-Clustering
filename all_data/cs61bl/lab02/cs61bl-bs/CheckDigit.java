public class CheckDigit {

	public static void main(String[] args) {
		int id = 0;
		try {
			id = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println("The argument has to be a sequence of digits.");
			System.exit(1);
		}

		boolean isLegal = true;
		int total = 0;
		int last_digit = id % 10;
		int rest_id = id / 10;

		while (rest_id > 9) {
			int last = rest_id % 10;
			rest_id = rest_id / 10;
			total += last;
		}
		total += rest_id;
		if ((total % 10) != last_digit)
			isLegal = false;
		if (isLegal) {
			System.out.println(id + " is legal");
		} else {
			System.out.println(id + " is not legal");
		}
	}

}
