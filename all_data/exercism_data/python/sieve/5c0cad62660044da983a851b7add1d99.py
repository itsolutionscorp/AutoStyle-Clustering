def sieve(n):
	ints = range(3, n + 1, 2) # contains only odd numbers excluding 1
	count = 0
	while count < len(ints):
		currentprime = ints[count] # takes the next number in the list of ints. it should be a prime
		ints = [currentprime] + list(filter((lambda x: x % currentprime <> 0), ints)) # filters any multiples of that prime from the ints list
		count += 1
	if n >= 2:
		ints.append(2) # adds 2 back into the list
	ints.sort() #sorts the list to put the numbers back into correct order
	return ints
