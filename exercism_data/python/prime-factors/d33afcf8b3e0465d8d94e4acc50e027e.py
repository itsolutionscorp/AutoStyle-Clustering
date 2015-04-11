import math

def _next_divisor(p):
	for q in range(2, int(math.sqrt(p)) + 1):
		if p % q == 0:
			return q
	return p

def prime_factors(p):
	factors = []
	if p < 1:
		return factors
	while p > 1:
		fac = _next_divisor(p)
		factors.append(fac)
		p /= fac
	return factors
