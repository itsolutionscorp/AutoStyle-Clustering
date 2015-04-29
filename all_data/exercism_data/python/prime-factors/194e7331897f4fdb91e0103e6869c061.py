import sys

#sys.setrecursionlimit(15000)

primes = []

def prime_factors(n):
	global primes 
	primes 	= [2]
	r = []
	p = primes[0]
	while n > 1:
		if n % p == 0:
			r.append(p)
			n = n/p
		else:
			p = nextPrime(p)
	return r

def nextPrime(n):
	global primes
	nopr = False
	while not nopr:
		n += 1
		nopr = getSieve(n,primes)
	primes.append(n)
	return n
		
def getSieve(n,l):
	if not any(n%x == 0 for x in l):
		return True
	else:
		return False
