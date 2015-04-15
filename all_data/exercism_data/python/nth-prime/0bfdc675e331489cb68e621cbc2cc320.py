import math
import numpy

# fastest Python prime sieve - http://rebrained.com/?p=458
def prime6(upto):
    primes=numpy.arange(3,upto+1,2)
    isprime=numpy.ones((upto-1)/2,dtype=bool)
    for factor in primes[:int(math.sqrt(upto))]:
        if isprime[(factor-2)/2]:
		isprime[(factor*3-2)/2::factor]=0
    return numpy.insert(primes[isprime],0,2)


def nth_prime(n):
	# http://en.wikipedia.org/wiki/Prime_number_theorem	
	# approximation for n-th prime is p(n) = n . log(n)
	upto = max(100, n * math.log(n))
	primes = []
	while len(primes) < n:
		primes = prime6(int(upto))
		upto *= 1.1
	return primes[n-1]
	
