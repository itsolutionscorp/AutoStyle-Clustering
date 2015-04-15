import itertools
import operator
from bitarray import bitarray


weight_tab = {
	6:	[1100, 10010, 100001],
	5:	[100, 1010, 10001],
	4:	[110, 1001],
	3:	[10, 101],
	2:	[11],
	1:	[1]
}


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

primes = get_primes(1000)


def prime_factors(number):

	res = []
	for prime in primes:

		while number % prime == 0:
			res.append(prime)
			number /= prime

			if number == 1:
				return res

	return res


def get_factors(palindrome, range):

	factors = prime_factors(palindrome)
	res = set(factors) | {
		reduce(operator.mul, x)
		for n in xrange(2, (len(factors) + 3) >> 1)
		for x in list(itertools.combinations(factors, n))
	}

	return sorted([
		x
		for x in res
		if range[0] <= x <= range[1]
	])


def init_state(factor):

	product = factor ** 2
	s = str(product)
	length = len(s)

	#	internal starting state for the counter
	t = s[:(length+1)/2]
	state = [int(char) for char in t[::-1]]

	return state, length


def get_increasing_palindromes(range):

	min_factor, max_factor = range
	state, length = init_state(min_factor)
	max_product = max_factor ** 2

	while True:

		weights = weight_tab[length]
		palindrome = sum(a * b for a, b in zip(state, weights))
		if palindrome > max_product:
			break

		yield palindrome

		n = 0
		while True:

			state[n] += 1
			state[n] %= 10
			if state[n] != 0:
				break

			n += 1
			if len(state) == n:
				if length & 1:
					state[-1] = 1
				else:
					state.append(1)
				length += 1
				break


def get_decreasing_palindromes(range):

	min_factor, max_factor = range
	state, length = init_state(max_factor)
	min_product = min_factor ** 2

	while True:

		weights = weight_tab[length]
		palindrome = sum(a * b for a, b in zip(state, weights))
		if palindrome < min_product:
			break

		yield palindrome

		n = 0
		while True:
			if state[n] == 1 and len(state) == n + 1:
				if length & 1:
					del state[n]
				else:
					state[n] = 9
				length -= 1
				break

			else:
				state[n] -= 1
				if state[n] >= 0:
					break

				state[n] = 9
				n += 1


def pal_func(pal_gen, order_func, comp_func, range, limit):

	for palindrome in pal_gen(range):
		for factor in order_func(get_factors(palindrome, range)):
			if comp_func(palindrome / factor, limit):
				return (palindrome, {factor, palindrome / factor})


def smallest_palindrome(max_factor, min_factor = 1):
	return pal_func(
		get_increasing_palindromes,
		list,
		operator.ge,
		(min_factor, max_factor),
		min_factor
	)


def largest_palindrome(max_factor, min_factor = 1):
	return pal_func(
		get_decreasing_palindromes,
		reversed,
		operator.le,
		(min_factor, max_factor),
		max_factor
	)
