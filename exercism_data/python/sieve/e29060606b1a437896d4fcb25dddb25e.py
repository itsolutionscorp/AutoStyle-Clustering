def sieve(limit):
	"""Returns prime numbers up to limit

	Keywords arguments:
	limit -- natural number
	"""
	if type(limit) is not int:
		raise TypeError
	if limit < 1:
		raise ValueError
	primes = []
	composites = []
	for number in xrange(2, limit+1):
		if number not in composites:
			primes.append(number)
			# optimze the algorithm by starting to add composites at square of the prime 
			for i in xrange(number*number, limit+1, number):
				composites.append(i)
	return primes
