def prime_factors(number):
	factors = []
	d = 2
	while number > 1:
		r, mod = divmod(number, d)
		if mod == 0:
			factors.append(d)
			number = r
		else:
			d += 1
	return factors
