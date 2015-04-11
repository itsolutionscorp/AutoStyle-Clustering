def sieve(limitval):
	'''
	Sieve of Eratosthenes to find primes <= limitval
	'''
	
	candidates = list(range(2,limitval))
	jj = 0
	nextprime = candidates[jj]
	while nextprime**2<=limitval:
		removelist = [nextprime*m for m in candidates if nextprime*m in candidates]
		candidates = [n for n in candidates if n not in set(removelist)]
		jj += 1
		nextprime = candidates[jj]
	
	return candidates
	
if __name__ == '__main__':
	print('primes <= 1000 are:')
	print(sieve(1000))
