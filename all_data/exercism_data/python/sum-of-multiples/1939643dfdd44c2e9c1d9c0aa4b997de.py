def sum_of_multiples(x, factors = [3, 5]):
	multiples = [i for i in xrange(0, x) 
					if is_divisible_by(i, factors)]
	return sum(multiples)

def is_divisible_by(i, factors):
	for j in factors:
		try:
			if i % j == 0:
				return True
		except:
			pass
	return False
