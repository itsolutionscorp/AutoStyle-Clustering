primes = [2, 3]

def nth_prime(num):
	current = primes[-1]
	while len(primes) < num:
		current += 1
		if not any(current % prime == 0 for prime in primes):
			primes.append(current)
		
	return primes[num - 1]
