import math

def sieve(n):
	primes = []
	for candidate in range(2, n + 1):
		if _not_multiple_of(candidate, primes):
			primes.append(candidate)
	return primes

def _not_multiple_of(candidate, primes):
	"""	True if `candidate` is not a multiple of anything in `primes` list.
		Otherwise False.
		Relies on `primes` being sorted in ascending order.
	"""

	# optimization: candidate can not be the multiple
	# of anything greater than its square root.
	limit = int(math.sqrt(candidate))

	for p in primes:
		if p > limit:
			return True
		elif candidate % p == 0:
			return False
	return True
