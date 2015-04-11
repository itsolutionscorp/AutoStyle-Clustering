def sieve(max_number):
	number_list = [x for x in range(2,max_number)]
	for y in number_list:
		evenly_divisible_list = [x for x in number_list if x%y==0 and x!=y]
		number_list = set(number_list) - set(evenly_divisible_list)
	return(sorted(number_list))
