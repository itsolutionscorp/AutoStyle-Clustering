from bitarray import bitarray

def get_primes(limit):

	primes = []
	sieve = bitarray(limit)
	sieve.setall(False)

	n = 2
	while n < limit:
		if sieve[n] == False:
			primes.append(n)
			sieve[2*n : limit : n] = True
		n += 1

	sieve = None
	return primes

prime_list = get_primes(1000)

def prime_factors(number):

	res = [1]
	for prime in prime_list:

		while number % prime == 0:
			res.append(prime)
			number /= prime

	return res


def is_coprime(a, b):

	while b:
		a, b = b, a%b
	return a == 1


def triplets(b, scale):
	try:
		return list(primitive_triplets(b, scale))
	except:
		return []


def triplets_in_range(lower, upper):

	"""
	Generate the pythagorean triplets whose elements
	all are inside the interval [lower, upper]
	"""

	#	iterate over the range, and generate triplets for
	#	all the factors of each number, and keep the triplets
	#	that are in range

	return {
		triplet
		for n in xrange(lower, upper + 1)
		for factor in prime_factors(n)
		for triplet in triplets(n / factor, factor)
		if triplet[0] >= lower and triplet[2] <= upper
	}


def is_triplet(t):
	a, b, c = sorted(list(t))
	return a*a + b*b == c*c


def primitive_triplets(b, scale=1):

	if b % 2:
		raise ValueError("odd")

	factors = prime_factors(b/2)
	l = len(factors)
	msb = 2 ** l

	res = set()
	for i in xrange(msb/2):

		#	generate a binary representation of *i*,
		#	and use that to select which prime factors to use

		weights = list(bin(msb+i)[3:])

		factor = 1
		for w, f in zip(weights, factors):
			if int(w):
				factor *= f

		m = factor
		n = (b/2) / factor

		#	make sure m > n
		if m < n:
			m, n = n, m

		#	make sure m, n are both odd
		if (m - n) % 2 == 0:
			continue

		#	and coprime
		if not is_coprime(m, n):
			continue

		l = tuple(sorted([scale*(m*m-n*n), scale*(m*m+n*n), scale*2*m*n]))
		res.add(l)

	return res
