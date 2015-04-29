# apply the Sieve of Eratosthenes to find all primes up to a given limit
def sieve(limit):
	candidates = list(range(2,limit+1))
	primes = []

	# select the next potential prime and delete all multiples of it
	while len(candidates):
		x = candidates[0]
		primes.append(x)
		del(candidates[0])
		# add 1 to include division floor, which is the largest multiple
		# potentially contained in the list
		for multiplier in range(2,int(limit/x)+1):
			multiple = x*multiplier
			if multiple in candidates:
				candidates.remove(multiple)

	return primes
