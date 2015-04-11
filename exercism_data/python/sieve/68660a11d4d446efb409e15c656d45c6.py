def sieve(l):
	return reduce(lambda a,b:putSieve(b,a),range(2,l+1),[])

def putSieve(n,l):
	if not any([n%x == 0 for x in l]):
		l.append(n)
	return l
