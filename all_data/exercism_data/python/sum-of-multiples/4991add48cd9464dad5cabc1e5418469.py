def sum_of_multiples(n, factors = [3,5]):
	"""Given a number returns the sum of all the multiples of factors up to but not including that number.

	Keyword arguments:
	n -- integer (non-negative); all multiples must be lower than n
	factors -- list of integers (non-negative) to be used as factors; default is [3, 5]
	"""
	result = set()
	for factor in factors:
		try:
			result = result | set(xrange(factor,n,factor))
		except ValueError:
			pass
		
	return sum(result)
