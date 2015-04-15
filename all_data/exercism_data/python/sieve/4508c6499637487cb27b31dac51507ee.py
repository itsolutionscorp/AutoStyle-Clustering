'''
Sieve of Eratosthenes to find primes <= limitval
'''

def sieve(limitval):
	candidates = set(range(2,limitval))
	jj = 0
	nextprime = sorted(candidates)[jj]
	while nextprime**2<=limitval:
		removelist = [nextprime*m for m in candidates]
		candidates = candidates.difference(removelist)
		jj += 1
		nextprime = sorted(candidates)[jj]
	return sorted(candidates)

if __name__ == '__main__':
	print('primes <= 1000 are:')
	print(sieve(1000))
