'''
Return the nth prime
'''

primes_to_1000 = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
		53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109,
		113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
		181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251,
		257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331,
		337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
		419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487,
		491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577,
		587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653,
		659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743,
		751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829,
		839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929,
		937, 941, 947, 953, 967, 971, 977, 983, 991, 997]

primes_to_10 = [2, 3, 5, 7]

def sieve(limitval):
	'''
	Sieve of Eratosthenes to find primes <= limitval
	'''

	candidates = set(range(2,limitval))
	jj = 0
	nextprime = sorted(candidates)[jj]
	while nextprime**2<=limitval:
		removelist = [nextprime*m for m in candidates]
		candidates = candidates.difference(removelist)
		jj += 1
		nextprime = sorted(candidates)[jj]

	return sorted(candidates)

def sieve2(limitval, smallprimes=[]):
	'''
	Sieve of Eratosthenes to find primes <= limitval by extending previous list of primes
	'''
	if not smallprimes:
		smallprimes = primes_to_1000

	if limitval<smallprimes[-1]:
		return([n for n in smallprimes if n < limitval])
	else:
		# remove all multiples of smallprimes from new candidates
		new_candidates = set(range(1+smallprimes[-1],1+limitval))
		for nextprime in smallprimes:
			multiple_range = range(min(new_candidates)//nextprime, 1+max(new_candidates)//nextprime)
			multiples = [nextprime*m for m in multiple_range]
			new_candidates = new_candidates.difference(multiples)
			if not new_candidates:
				break

		if not new_candidates:
			return smallprimes

		candidates = set(smallprimes).union(new_candidates)

		# now remove multiples of the new candidates from new candidates
		jj = len(smallprimes)
		nextprime = sorted(candidates)[jj]
		while nextprime**2<=limitval:
			multiples = [nextprime*m for m in candidates]
			candidates = candidates.difference(multiples)
			jj += 1
			nextprime = sorted(candidates)[jj]

	return sorted(candidates)


def nth_prime(n):
	'''return the nth prime, where nth_prime[1] = 2'''
	if n<1:
		return
	limitval = 0
	primelist = primes_to_1000
	while len(primelist)<n:
		limitval += 1000
		primelist = sieve2(limitval,primelist)
	return primelist[n-1]
