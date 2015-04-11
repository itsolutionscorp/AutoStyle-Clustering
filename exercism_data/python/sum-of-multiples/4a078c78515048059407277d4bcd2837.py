def sum_of_multiples(input_number, factors=[3, 5]):

	multiples = []
	for factor in factors:
		for i in range(1,input_number):
			if factor*i < input_number and factor*i not in multiples:
				multiples.append(factor*i)

	return sum(multiples)
