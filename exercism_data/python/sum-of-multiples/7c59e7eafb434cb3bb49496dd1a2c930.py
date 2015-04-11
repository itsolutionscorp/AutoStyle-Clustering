def sum_of_multiples(n, divisors=None):
	
	if not divisors:
		divs = [3,5]
	else:
		divs = divisors

	total = 0

	for x in range(1, n):
		for div in divs:
			if div != 0 and x % div == 0:
				total += x
				break

	return total
