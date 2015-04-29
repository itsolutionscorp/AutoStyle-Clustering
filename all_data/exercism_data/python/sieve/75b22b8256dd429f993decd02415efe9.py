def sieve(max):
	primes = []
	non_primes = []
	for i in range(2,max+1):
		if i not in non_primes:
			primes.append(i)
		non_primes.extend([x for x in range(i+1,max+1) if x%i == 0])
	return primes
	
