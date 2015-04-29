# generate list of prime factors of natural number n
def prime_factors(n):
	factors = []
	d = 2
	while d*d <= n:
		while (n%d) == 0:
			factors.append(d)
			n /= d
		d += 1
	if n > 1:
		factors.append(n) 	
	return factors
