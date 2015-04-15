from math import sqrt
primes = [2,3,5,7]

def nth_prime(n):
	m = primes[-1] + 1
	while len(primes) < n:
		prime = True
		for p in primes:
			if not m % p:
				prime = False
				break
			if p > sqrt(m):
				break
		if prime:
			primes.append(m)
		m += 1
	return primes[n-1]
