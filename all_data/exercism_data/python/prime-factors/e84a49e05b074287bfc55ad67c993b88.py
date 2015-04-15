def prime_factors(n):
	primes 	= [2]
	r = []
	p = primes[0]
	while n > 1:
		if n % p == 0:
			r.append(p)
			n = n/p
		else:
			p = nextPrime(p,primes)
			primes.append(p)
	return r

def nextPrime(n,primes):
	nopr = False
	while not nopr:
		n += 1
		nopr = getSieve(n,primes)
	return n
		
def getSieve(n,l):
	if not any(n%x == 0 for x in l):
		return True
	else:
		return False
