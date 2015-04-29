
def sieve(limit):
	primes = range(2, (limit+1))
	multiplier = 2
	while multiplier < limit:
		for number in range(multiplier, (limit + 1)):
			if number * multiplier in primes:
				primes.remove(number * multiplier)
		multiplier += 1
	return primes

