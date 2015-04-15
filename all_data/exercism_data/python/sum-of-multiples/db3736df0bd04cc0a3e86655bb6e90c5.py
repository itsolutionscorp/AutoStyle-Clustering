def sum_of_multiples(n, factors=[3, 5]):
	sum = 0
	for i in xrange(n):
		if any(f > 0 and i % f == 0 for f in factors):
			sum += i
	return sum
