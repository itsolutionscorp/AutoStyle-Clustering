def sieve(num):
	result = range(2,num+1)
	for n in result:
		for multiple in range(2, num/n+1):
			if multiple*n in result:
				result.remove(multiple*n)
	return result
