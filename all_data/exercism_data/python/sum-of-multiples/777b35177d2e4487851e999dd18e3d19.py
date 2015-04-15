def sum_of_multiples(n, factors = [3,5]):
	full_set = set([])
	for i in factors:
		if i != 0:
			factor_set = set(range(i, n, i))
		else:
			factor_set = set([0])
		full_set = full_set.union(factor_set)

	return sum(full_set)
