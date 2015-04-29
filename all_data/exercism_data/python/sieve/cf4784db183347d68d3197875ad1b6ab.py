def sieve(n):
	isprime = [True] * (n+1)
	primes = []
	for i in range(2,n+1):
		if isprime[i]:
			primes.append(i)
		for j in range(i*i, n+1, i):
			isprime[j] = False
	return primes

	
