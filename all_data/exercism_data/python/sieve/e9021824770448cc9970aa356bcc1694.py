def sieve(limit):
	primes = range(2, limit)
	for i in primes:
		not_primes = [x for x in primes if x % i == 0]
		primes = [x for x in primes if x not in not_primes or x == i]
	return primes
