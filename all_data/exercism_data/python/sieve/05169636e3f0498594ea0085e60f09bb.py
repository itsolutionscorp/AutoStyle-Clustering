def sieve(limit):
	primes = [2]
	square_root = limit ** 0.5
	
	flags = [True] * limit

	for index in range(3, limit, 2):
		if flags[index]:
			primes.append(index)

			if index <= square_root:
				for j in range(index*index, limit, index<<1):
					flags[j] = False

	return primes
