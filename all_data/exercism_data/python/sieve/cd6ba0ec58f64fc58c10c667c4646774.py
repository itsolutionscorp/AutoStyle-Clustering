def sieve(l):
	r = []
	for x in range(2,l+1):
		r = putSieve(x,r)
	return r

def putSieve(n,l):
	if not any([n%x == 0 for x in l]):
		l.append(n)
	return l
