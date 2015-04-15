"""
Exercise 4 - Hamming Test
"""
def distance(orig, test):
	"""
	Zips the two lists together
	Compares the individual letters
	Multiplies by 1 to turn True -> 1, False -> 0
	Sums the result (# of different letters)
	"""
	return sum([(x != y) * 1 for x, y in zip(orig, test)])
