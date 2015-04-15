def prime_factors(n):
	prime_factors = []	
	factor = 2
	while factor <= n:
		while n % factor == 0 and n > 1:
			prime_factors.append(factor)
			n /= factor	
		factor += 1
	return prime_factors
