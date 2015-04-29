def sieve(limit):
	if limit < 2:
		raise ValueError("the limit must be grater than 2")
	
	primes = [2]
	
	for k in range (2+1,limit):
		is_prime = True
		for j in primes:
			if k%j == 0:
				is_prime = False
				break
		if is_prime:				
			primes.append(k)

	return primes
