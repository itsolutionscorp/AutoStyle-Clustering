numbers = [ 	[" _ ", "| |", "|_|", "   "], #0
		["   ", "  |", "  |", "   "], #1
		[" _ ", " _|", "|_ ", "   "], #2
		[" _ ", " _|", " _|", "   "], #3
		["   ", "|_|", "  |", "   "], #4
		[" _ ", "|_ ", " _|", "   "], #5
		[" _ ", "|_ ", "|_|", "   "], #6
		[" _ ", "  |", "  |", "   "], #7
		[" _ ", "|_|", "|_|", "   "], #8
		[" _ ", "|_|", "  |", "   "]] #9

def validate(number):
	if any(len(num) != 3 for num in number) or len(number) != 4:
		raise ValueError("Number does not exist")

def number(number):
	validate(number)
	for pos, item in enumerate(numbers):
		if item == number:
			return str(pos)
	return '?'

def grid(number):
	validate(number)
	return numbers[number]
