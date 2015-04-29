"""

> There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the product a * b * c.

It seems that the problem has very little to do with unit tests provided, for some reason...

"""

import math, itertools, operator, fractions

#
# Actual problem solution
#
def triplets_by_sum(total):
	""" Generate all Pythagorean triplets with a + b + c = `total`.

		Any Pythagorean triplet c**2 = a**2 + b**2 (not necessary co-prime) can be writted as:

		a = k * (m**2 - n**2), 		// m > n > 0, k > 0
		b = k * 2mn
		c = k * (m**2 + n**2)

		Since we know that a + b + c = total:

		2m**2 + 2mn = total / k
		2mk(m+n) = total

		To find all Pythagorean triplets with a + b + c = total
		we need to find all variants of (m, n, k) such that
		1) 2mk(m+n) = total
		2) m > n > 0
		3) k > 0

		That's what the algorithm is based on.
	"""
	if total % 2 == 0:
		for k in _factors(total // 2):
			for m, m_plus_n in _factorize(total // 2 // k):
				n = m_plus_n - m
				if m > n and n > 0:
					yield tuple(sorted((k*m*m - k*n*n, 2*m*n*k, k*m*m + k*n*n)))

def _factorize(num):
	""" Factorize `num` into 2 multipliers in all possible ways, yielding unique results.
		Returned tuples are sorted.
	"""
	processed = set()
	for a in _factors(num):
		a, b = sorted((num // a, a))
		yield (a, b)
		processed.add(a)
		processed.add(b)

def _factors(num):
	""" Generate all numbers `num` is divisible by, not necessary prime.
		This includes 1 and `num`.
	"""
	processed = set()
	prime_factors = _prime_factors(num)
	for i in range(len(prime_factors) + 1):
		for a_factors in itertools.combinations(prime_factors, i):
			a = reduce(operator.mul, a_factors, 1)
			if a not in processed:
				yield a
				processed.add(a)

def _prime_factors(n):
	""" List of prime factors of a given natural number in ascending order.
	"""
	result = []
	limit = int(math.sqrt(n))
	for p in _primes():
		while n % p == 0:
			result.append(p)
			n //= p
			limit = int(math.sqrt(n))
		if n == 1:
			return result
		if p > limit:
			result.append(n)
			return result

def _primes():
	""" Generate prime numbers from 2 to infinity
	"""
	primes = []
	for candidate in itertools.count(2):
		if _not_multiple_of(candidate, primes):
			yield candidate
			primes.append(candidate)

def _not_multiple_of(candidate, primes):
	"""	True if `candidate` is not a multiple of anything in `primes` list.
		Otherwise False.
		Relies on `primes` being sorted in ascending order.
	"""
	limit = int(math.sqrt(candidate))
	for p in primes:
		if p > limit:
			return True
		elif candidate % p == 0:
			return False
	return True




#
# Included to pass unit-test provided
#

# Super inefficient implementation to test `triplets_by_sum` using the unittest provided
def triplets_in_range(rmin, rmax):
	result = set()
	for i in range(rmin*3, rmax*3):
		for triplet in triplets_by_sum(i):
			if all(v >= rmin and v <= rmax for v in triplet):
				result.add(triplet)
	return result

# Super inefficient implementation to test `_factorize` using the unittest provided
def primitive_triplets(b):
	if b % 2 == 1:
		raise ValueError("b must be even")
	result = set()
	for n, m in _factorize(b // 2):
		if fractions.gcd(m, n) == 1:
			result.add(tuple(sorted((m*m - n*n, 2*m*n, m*m + n*n))))
	return result

# Code to pass unit tests, not used anywhere else
def is_triplet(tri):
	a, b, c = sorted(tri)
	return c**2 == a**2 + b**2
