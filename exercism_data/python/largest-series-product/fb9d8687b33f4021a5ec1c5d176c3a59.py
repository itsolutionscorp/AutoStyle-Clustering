import operator

def slices(string, length):

	if length == 0:
		raise ValueError("Zero length")

	if length > len(string):
		raise ValueError("Too short string")

	return [
		map(int, list(c))
		for c in (
			string[n:n+length]
			for n in xrange(len(string) - length + 1)
		)
	]

def largest_product(string, length):

	return max(
		reduce(operator.mul, series)
		for series in slices(string, length)
	) if length else 1
