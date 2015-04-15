def sieve(n):
	ints = range(2, n)
	count = 0
	while count < len(ints):
		currentprime = ints[count]
		multiple = currentprime
		while multiple < n:
			multiple = multiple + currentprime
			if multiple in ints:
				ints.remove(multiple)
		count = count + 1
	return ints
