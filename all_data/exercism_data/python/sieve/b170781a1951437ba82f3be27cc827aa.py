def sieve(limit):
	primes = []
	pRange = range(limit, 1, -1)
	while(pRange):
		p = pRange.pop()
		pRange = filter(lambda x: x % p != 0, pRange)
		primes.append(p)
	return primes
