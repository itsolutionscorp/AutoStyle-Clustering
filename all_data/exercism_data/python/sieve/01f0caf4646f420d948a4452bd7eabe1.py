def sieve(limit):
	primes = [2]
	pRange = range(3,limit,2)
	while(pRange):
		p = pRange.pop(0) #ith element to pop
		pRange = filter(lambda x: x % p != 0, pRange)
		primes.append(p)
	return primes
