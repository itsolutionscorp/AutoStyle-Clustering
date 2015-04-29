def _sieve(n):
	primes = [True] * n
	primes[0] = primes[1] = False
	for g, prime in enumerate(primes):
		if prime:
			yield g
			for i in xrange(g, n+1/2, g):
				primes[i] = False

def sieve(n):
	return list(_sieve(n))
