from itertools import count

def prime_factors(num):
	step = num
	facts = []
	for x in count(2):
		if step == 1: break
		while True:
			if step % x == 0: 
				facts.append(x)
				step /= x
			else: break
	return facts
