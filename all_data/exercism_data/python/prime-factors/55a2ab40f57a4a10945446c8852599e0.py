import math

def is_prime(n):
	check = True
	for count in range(2, int(n**0.5)+1):
		if n % count == 0:
			check = False
			break
	if n == 1:
		check = False
	return check

def prime_factors(n):
	primefactors = []
	factor = 2
	# deals with factor = 2 seperately so that the next loop can increment by 2 so that only odd numbers are tried
	while n % 2 == 0:
		primefactors += [factor]
		n /= factor
	factor += 1
	while n <> 1:
		if is_prime(factor):
			if n % factor == 0:
				primefactors += [factor]
				n /= factor
			else:
				factor += 2
		else:
			factor += 2
	primefactors.sort()
	return primefactors
