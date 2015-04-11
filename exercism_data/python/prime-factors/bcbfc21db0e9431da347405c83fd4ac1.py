def rwh_primes(n):
	# http://stackoverflow.com/questions/2068372/fastest-way-to-list-all-primes-below-n-in-python/3035188#3035188
	""" Returns  a list of primes < n """
	sieve = [True] * n
	for i in range(3,int(n**0.5)+1,2):
		if sieve[i]:
			sieve[i*i::2*i] = [False]*(int)((n-i*i-1)/(2*i)+1)
	return [2] + [i for i in range(3,n,2) if sieve[i]]


def prime_factors(n):
	'''
	determine prime factors of a number (n)
	'''
	primelist = rwh_primes((int) (n**0.5) + 1)
	factors = []
	for prime in primelist:
		while n % prime == 0:
			factors = factors + [prime]
			n = n // prime
	return factors + [n] if n>1 else factors


if __name__ == '__main__':
	print(60, prime_factors(60))
	print(2, prime_factors(2))
	print(1, prime_factors(1))
	print(121, prime_factors(121))
	print(67, prime_factors(67))
