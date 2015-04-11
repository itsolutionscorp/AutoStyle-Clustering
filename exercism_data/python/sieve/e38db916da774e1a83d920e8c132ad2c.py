from math import sqrt

def sieve(upto):
	list = [2]
	next = 3
	while (next <= upto):
		if test(next, list):
			list.append(next)
		next += 2
	return list

def test(candidate, primes):
	result = True
	limit = sqrt(candidate)
	for p in primes:
		if (p > limit):
			break
		if ((candidate % p) == 0):
			result = False
			break
	return result
