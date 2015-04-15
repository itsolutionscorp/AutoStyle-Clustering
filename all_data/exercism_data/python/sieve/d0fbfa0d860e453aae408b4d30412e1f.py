from math import sqrt

def sieve(n):

	numbers = [x for x in range(1, n+1)]

	total = []

	for number in numbers:
		if is_prime(number):
			total.append(number)
	return total

def is_prime(x):
	x = int(x)
	if x == 2 or x == 3:
		return True
	if x < 2 or x%2 == 0:
		return False
	if x < 9:
		return True
	if x % 3 == 0:
		return False
	r = int(sqrt(x))
	f = 5
	while f <= r:
		if x%f == 0:
			return False
		if x%(f+2) == 0:
			return False
		f += 6
	return True
