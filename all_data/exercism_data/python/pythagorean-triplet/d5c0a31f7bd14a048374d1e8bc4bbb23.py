from math import sqrt

def primitive_triplets(b):
	if b % 4 != 0: raise ValueError('not divisible by 4!')
	hi = b**2
	triplets = []
	for a in xrange(1, hi):
		c = sqrt(a**2 + b**2)
		if c > hi: break
		if c == int(c): 
			triplet = tuple(sorted([a, b, int(c)]))
			if is_triplet(triplet):
				triplets.append(triplet)
	return set(triplets)
	
def triplets_in_range(low, hi):
	triplets = []
	for a in xrange(low, hi + 1):
		for b in xrange(a, hi + 1):
			c = sqrt(a**2 + b**2)
			if c > hi: break
			if c == int(c): triplets.append((a, b, int(c)))
	return set(triplets)

def is_triplet(triplet):
    factors = [set(prime_factors(num)) for num in triplet]
    return set.intersection(*factors) == set([1])

def prime_factors(n):
	return set(reduce(list.__add__,
		([i, n//i] for i in range(1, int(n**0.5) + 1) if n % i == 0)))
