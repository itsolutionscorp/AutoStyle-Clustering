def get_primes(limit):

	from bitarray import bitarray

	primes = []
	sieve = bitarray(limit)
	sieve.setall(False)

	n = 2
	while n < limit:
		if sieve[n] == False:
			primes.append(n)
			sieve[2*n : limit : n] = True
		n += 1

	sieve = None
	return primes

prime_list = get_primes(105000)

def nth_prime(n):
	return prime_list[n-1]
