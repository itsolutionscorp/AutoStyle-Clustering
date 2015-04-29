def sieve(list_of_nums_end):
	num_list = list(range(2, list_of_nums_end+1))

	for a in num_list:
		for b in num_list:
			if a!=b and b%a==0:
				num_list.remove(b)

	return num_list
