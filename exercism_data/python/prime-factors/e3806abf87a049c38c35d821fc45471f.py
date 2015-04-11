'''prime_factors.py
	created 2 Nov 2014
	by @jestuber '''

def prime_factors(num):
	divisor = 2
	left_with = num
	factors = []
	while left_with > 1:
		if left_with % divisor == 0:
			factors.append(divisor)
			left_with = left_with / divisor
		else:
			divisor += 1

	return factors
