def sum_of_multiples(num, m=[3, 5]):
	multiples = []
	for i in range(num):
		for item in m:
			if item != 0 and i % item == 0:
				multiples.append(i)
				break

	return sum(multiples)
