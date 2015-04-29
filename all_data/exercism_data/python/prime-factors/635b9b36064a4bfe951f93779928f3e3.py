def prime_factors(num):
	factors=[]
	i = 2
	while i <= num:
		if (not any([i%j == 0 for j in factors])) and num % i == 0:
			while not num % i:
				factors.append(i)
				num /= i
		i += 1
	return factors
