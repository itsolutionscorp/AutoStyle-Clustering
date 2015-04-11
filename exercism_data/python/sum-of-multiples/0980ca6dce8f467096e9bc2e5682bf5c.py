def sum_of_multiples(limit, factors = [3, 5]):

	f_set = set()

	res = 0
	for factor in factors if factor:
		end = (limit - 1) / factor

		for i in xrange(1, end + 1):
			if i*factor not in f_set:
				res += i*factor
				f_set.add(i*factor)

	return res
