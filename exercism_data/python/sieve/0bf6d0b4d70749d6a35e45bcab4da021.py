def sieve(n):
	numbers = range(2,n+1)
	primes = []

	while(1):
	
		done = 1
		
		for p in numbers:
			if (p == 0):
				continue
			else:
				done = 0
				primes.append(p)
				break
		
		if done:
			return primes

		for x in range(1,n):
			prod = x * p
			if (prod <= n):
				numbers[prod - 2] = 0
			else:
				break


	
