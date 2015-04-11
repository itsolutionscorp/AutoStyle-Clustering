primes = [2,3,5,7]

def nth_prime(n):
	m = primes[-1] + 1
	while len(primes) < n:
		prime = True
		i = 0
		while prime and primes[i] <= m**0.5:
			prime = m % primes[i]
			i += 1
		if prime:
			primes.append(m)
		m += 1
	return primes[n-1]
