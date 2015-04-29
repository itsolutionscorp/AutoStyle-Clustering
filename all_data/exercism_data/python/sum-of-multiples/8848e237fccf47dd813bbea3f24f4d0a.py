def sum_of_multiples(*arg):
	number = arg[0]
	if (len(arg) == 1):
		factors = [3, 5]
	else:
		factors = arg[1]
		factors.sort()
		if 0 in factors:
			factors = factors[1:]
	
	multiples = []
		
	for x in factors:
		for i in range(1, number):
			if ((i % x) == 0):
				if (i not in multiples):
					multiples.append(i)
				
	return sum(multiples)
	
