from sieve import sieve

def prime_factors(num):
	primes = sieve(num)
	index = 0
	prime_factor = []
	number_check = num

	while index != len(primes) and number_check != 1:
		prime_num = primes[index]
		if number_check%prime_num == 0:
			prime_factor.append(prime_num)
			number_check/=prime_num
		else:
			index += 1

	return prime_factor
