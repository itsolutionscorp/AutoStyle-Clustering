def prime_factors(n):
    factors = []
    if n > 1:
        next(n, factors)
    return factors
	
def next(k, factors):
    i = 2
    while k % i:
        i += 1
    factors.append(i)
    if k / i > 1:
        next(k / i, factors)
