from bitarray import bitarray

def sieve(limit):

	limit += 1
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
