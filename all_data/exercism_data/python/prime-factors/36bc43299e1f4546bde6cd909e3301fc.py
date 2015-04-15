from math import sqrt

#Changed based on comment from exercism user mmakaay... runs faster now
def prime_factors(num):
	factors = []
	for possible in range(2,int(sqrt(num))+1):
		while num%possible==0:
			factors.append(possible)
			num = num/possible
	if num > 1:
		factors.append(num)
	return factors
