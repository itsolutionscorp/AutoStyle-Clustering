def sieve(n): 
	"""Takes int n. Returns list of all prime numbers from 2 to n (not inclusive)."""
	
	assert n > 2 
	
	possible_primes = []
	
	for number in range(2, n): 
		possible_primes.append(number)
	
	for checker in possible_primes: 
		for x in reversed(xrange(len(possible_primes))): 
			if possible_primes[x] == checker: 
				continue
			elif possible_primes[x] % checker == 0: 
				del possible_primes[x]
			
	return possible_primes
