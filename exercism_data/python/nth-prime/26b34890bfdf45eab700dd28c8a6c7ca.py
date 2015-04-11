primes = [3]

def nth_prime(n):
	if n == 1:
		return 2

	if n > len(primes) + 1:
		candidate = primes[-1] + 2
		while len(primes) + 1 < n:
			if is_prime(candidate):
				primes.append(candidate)
			candidate += 2
	
	return primes[n - 2]

def is_prime(n):
	if n % 2 == 0:
		return False
	for i in range(3, int(n**.5) + 1, 2):
		if n % i == 0:
			return False
	return True
