def sum_of_multiples(limit, factors = [3, 5]):

	factor_set = set()
	for factor in factors:
		if factor:
			end = (limit - 1) / factor
			for i in xrange(1, end + 1):
				factor_set.add(i * factor)

	return sum(list(factor_set))
