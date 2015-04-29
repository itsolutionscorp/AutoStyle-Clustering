__author__ = "LeCuke"
__version__ = 2.0

def sieve(num):
	"""
	::n:: An integer number bigger than 2
	<return> all the primes from 2 up to ::n::
	""" 
	primes = range(2, num+1)
	i = 0
	while i < len(primes):
		prime = primes[i]
		for n in primes :
			if not n%prime and n != prime : # if the rest of i/p is 0, 'i' is not a prime number
				primes.remove(n)
		i += 1
	return primes
