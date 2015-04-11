def sum_of_multiples(inputx, factors=[3,5]):
	results = set()
	for i in range(inputx):
		for factor in factors:
			if factor is not 0 and i % factor == 0:
				results.add(i)
	return sum(results)
