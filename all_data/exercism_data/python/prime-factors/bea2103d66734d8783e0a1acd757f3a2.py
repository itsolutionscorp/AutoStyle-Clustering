from bitarray import bitarray

def get_primes(limit):

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

prime_list = get_primes(1000000)

def prime_factors(number):

	res = []
	for prime in prime_list:

		while number % prime == 0:
			res.append(prime)
			number /= prime

	return res
