def sum_of_multiples(until, factors=[3,5]):
	result = []
	for factor in factors:
		result += [n * factor for n in range(0,until) if n * factor < until]
	return reduce(lambda a,b: a+b, set(result))
