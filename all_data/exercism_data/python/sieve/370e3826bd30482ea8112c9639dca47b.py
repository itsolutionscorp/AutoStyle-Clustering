def sieve (until):
	result = []
	for num in xrange(2,until+1):
		is_prime = True
		for div in range(2, num):
			if num % div == 0:
				is_prime = False
				break
		if is_prime:
			result.append(num)
	return result
