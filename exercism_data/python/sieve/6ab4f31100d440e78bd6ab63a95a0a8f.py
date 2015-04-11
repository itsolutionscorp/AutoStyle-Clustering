def sieve(x):
	numbers = range(2, x + 1)
	for i in numbers:
		counter = 2
		while i*counter <= x:
			if i*counter in numbers:
				numbers.remove(i*counter)
			counter += 1

	return numbers
