decimal=[1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
roman=["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]
table = sorted(zip(decimal, roman), reverse=True)

def numeral(number):
	string = ""
	for decimal, roman in table:
		if number >= decimal:
			quantity = number / decimal
			string += roman * quantity
			number -= decimal * quantity
	return string
