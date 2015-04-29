def sieve(N):
	""" This program takes a natural number, N, and gives all primes <= N by using the Sieve of Eratosthenes."""
	starting_list = [i for i in range(2,N+1)]
	
	index = 0
	
	while index < (len(starting_list)):
		for list_index, integer in enumerate(starting_list):
			if (integer % starting_list[index] == 0 and integer != starting_list[index]):
				starting_list.pop(list_index)
		index += 1
	
	return starting_list
