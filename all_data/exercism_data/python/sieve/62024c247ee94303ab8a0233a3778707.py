def sieve(number):
	primes = range(2, number)
	for i in primes:
		for p in range(i*2, number, i):
			if p in primes:
				primes.remove(p)
	return primes
