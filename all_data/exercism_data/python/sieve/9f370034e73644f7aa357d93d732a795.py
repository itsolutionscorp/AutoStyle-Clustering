def sieve(limit):
	primes = range(2, limit)
	i = 0
	
	while i < len(primes):
			x = primes[i]

			for y in primes:
				if y % x == 0 and y != x and y in primes:
					primes.remove(y)

			i += 1

	return primes
