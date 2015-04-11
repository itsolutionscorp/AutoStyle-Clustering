__author__ = "LeCuke"

def sieve(n):
	"""
	::n:: An integer number bigger than 2
	<return> all the primes from 2 up to ::n::
	""" 
	primes = []
	for i in range(2, n+1):
		if not primes:
			primes.append(i)
			continue
		stop = False
		for p in primes:
			if not i%p: # if the rest of i/p is 0, 'i' is not a prime number
				stop = True
				break
		if not stop:
			primes.append(i)
	return primes
