def prime_factors(num):
	i = 2
	result = []
	while i * i <= num:
		if num % i:
			i += 1
		else:
			num //= i
			result.append(i)
	
	if num > 1:
		result.append(num)
	
	return result
