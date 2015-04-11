def sieve(limit):
	primes = []
	for k in range (2,limit):
		primes.append(k)
		
	this_prime = 2
	while this_prime < primes[len(primes)-1]:
		for p in primes:
			if p > this_prime and p%this_prime == 0:
				primes.remove(p)
		# checking for the last prime...
		if primes.index(this_prime) >= (len(primes)-1):
			break
		this_prime = primes[primes.index(this_prime)+1]
	return primes
