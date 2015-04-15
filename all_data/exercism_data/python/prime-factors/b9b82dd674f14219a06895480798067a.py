import math, itertools

def prime_factors(n):
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
