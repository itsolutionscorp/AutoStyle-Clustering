import math

def sieve(number):
	numbers = list(range(2, number + 1))
	for i in range(2, int(math.sqrt(number))+1):
		numbers[:] = [x for x in numbers if not (x%i==0 and x!=i)]
	return numbers
