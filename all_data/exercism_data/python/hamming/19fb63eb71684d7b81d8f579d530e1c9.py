"""
Exercise 4 - Hamming Test
"""
def distance(orig, test):
	"""
	Compares each character to see if they are not equal, 
	and saves the result in a list of 1s and 0s. Returns
	the sum of that list.
	"""
	comparison = [(orig[x] != test[x]) * 1 for x in range(len(orig))]
	return sum(comparison)
