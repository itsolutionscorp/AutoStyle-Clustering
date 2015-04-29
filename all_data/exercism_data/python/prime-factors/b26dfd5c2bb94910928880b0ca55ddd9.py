def prime_factors(num):
	number = num
	divisor = 2
	primes = []

	while number > 1:
		if number%divisor == 0:
			primes.append(divisor)
			number/=divisor
		else:
			divisor += 1

	return primes
