def sieve(number):
	unmarked_list = range(2, number)
	for x in unmarked_list:
		counter = 2
		while x*counter <= number:
			if x*counter in unmarked_list:
				unmarked_list.remove(x*counter)
			counter += 1
	return unmarked_list
