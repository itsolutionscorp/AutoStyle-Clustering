"""
Exercise 4 - Hamming Test
"""
def distance(orig, test):
	"""
	Zips the two lists together
	Compares the individual letters
	Sums the result (# of different letters)
	"""
	return sum([x != y for x, y in zip(orig, test)])
