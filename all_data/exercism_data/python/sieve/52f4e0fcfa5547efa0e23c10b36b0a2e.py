''' sieve.py
	created 4 Oct 2014
	by @jestuber '''

def sieve(n):
	'''Use Sieve of Eratosthenes to find Primes from 2 to n'''
	p = range(2,n)
	i=0
	while i < len(p):
		p = [x for x in p if x % p[i] != 0 or x == p[i]]
		i+=1

	return p
