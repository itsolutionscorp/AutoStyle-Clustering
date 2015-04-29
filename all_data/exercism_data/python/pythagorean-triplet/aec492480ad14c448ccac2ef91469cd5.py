from math import sqrt

def primitive_triplets(b):
	if b % 4 != 0: raise ValueError('not divisible by 4!')
	mn = b / 2
	triplets = set()
	for m in xrange(1, mn + 1):
		if mn % m == 0:
			n = mn / m
			if m > n and (m-n) % 2 != 0:
				is_coprime = True
				for i in xrange(2, n+1):
					if m % i == 0 and n % i == 0:
						is_coprime = False
						break
				
				if is_coprime:
					a = m**2 - n**2
					c = m**2 + n**2
					triplets.add(tuple(sorted((a, b, c))))
			
	return triplets
	
def triplets_in_range(low, hi):
	triplets = []
	for a in xrange(low, hi + 1):
		for b in xrange(a, hi + 1):
			c = sqrt(a**2 + b**2)
			if c > hi: break
			if c == int(c): triplets.append((a, b, int(c)))
	return set(triplets)

def is_triplet(triplet):
	# make sure a**2 + b**2 == c**2
	triplet = sorted(i**2 for i in triplet)
	if triplet[0] + triplet[1] != triplet[2]: return False
	
	# make sure they do not have a common factor other than 1
	for factor in xrange(2, min(triplet)+1):
		if all(i % factor == 0 for i in triplet):
			return False
			
	return True
