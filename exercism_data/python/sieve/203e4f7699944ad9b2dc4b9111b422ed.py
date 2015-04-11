def sieve(num):
	primes = []
	number_range = range(2, num+1)

	while len(number_range) > 0:
		index = 0
		prime_num = number_range.pop(0)
		primes.append(prime_num)
		while index < len(number_range):
			if number_range[index] % prime_num == 0:
				number_range.pop(index)
			else:
				index += 1

	return primes
