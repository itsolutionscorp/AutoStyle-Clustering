def prime_factors(num):
	factors = []
	for possible in sieve(num/2):
		while num%possible==0:
			factors.append(possible)
			num = num/possible
	if num > 1:
		factors.append(num)
	return factors

def sieve(num):
	result = range(2,num+1)
	for n in result:
		for multiple in range(2, num/n+1):
			if multiple*n in result:
				result.remove(multiple*n)
	return result
