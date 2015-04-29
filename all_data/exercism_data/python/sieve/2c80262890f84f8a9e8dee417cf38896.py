def sieve(limit):
	primes = []
	for candidate in range(2, limit):
		if isPrime(candidate):
			primes.append(candidate)
	return primes

def isPrime(x):
	for i in range(2, x-1):
		if x % i == 0:
			return False

	return True
