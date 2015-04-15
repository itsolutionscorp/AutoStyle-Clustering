import math

def sieve(givenrange):
	keys = [x+2 for x in xrange(givenrange-1)]
	bools = [True for x in xrange(givenrange-1)]
	sieve = dict(zip(keys, bools))
	results = []

	for i in xrange(2, int(math.sqrt(givenrange))+1):
		if sieve[i]:
			x=0
			for j in xrange(i**2, givenrange+1, i):
				sieve[j] = False
				x += 1

	for key, value in sieve.iteritems():
		if value:
			results.append(key)

	return results
