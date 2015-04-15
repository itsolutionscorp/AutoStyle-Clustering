def sum_of_multiples(n, factors = [3,5]):
	"""Given a number returns the sum of all the multiples of factors up to but not including that number.

	Keyword arguments:
	n -- integer (non-negative); all multiples must be lower than n
	factors -- list of integers (non-negative) to be used as factors; default is [3, 5]
	"""
	partial_sums = []
	for factor in factors:
		try:
			partial_sums.append(sum(xrange(factor, (n-1)/factor*factor+1, factor)))
			#partial_sums.append((n-1)/factor*(factor+(n-1)/factor*factor)/2)			# From formula for sum of arithmetic sequence
		except ZeroDivisionError:
			partial_sums.append(0)
	return sum(partial_sums)
