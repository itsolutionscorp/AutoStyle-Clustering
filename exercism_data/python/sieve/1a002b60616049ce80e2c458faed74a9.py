def sieve(n):
	sieved = []
	candidates = range(2,n)
	while candidates:
		next_prime = candidates.pop(0)
		sieved.append(next_prime)
		candidates = [x for x in candidates 
			if x not in range(next_prime,n,next_prime)]
	return sieved
