import math

def coprime(a, b):
	if a > b:
		return coprime(b, a)
	if a == 0:
		return b == 1
	return coprime(b % a, a)

def factors(n):
	up_to = int(n**0.5)
	return [ (n/i, i) for i in range(1,up_to+1) if n % i == 0]

def primitive_triplets(even):
	if even % 2 == 1:
		raise(ValueError('Must supply even number'))
	triplets = {tuple(sorted((m**2-n**2,even,m**2+n**2)))
		for (m,n) in factors(even/2)}
	return {(a,b,c) for (a,b,c) in triplets if coprime(a,b)}

def triplets_in_range(start, end):
	triplets = set()
	for i in range(4, end+1, 2):
		triplets |= primitive_triplets(i)
	extras = set()
	for (a,b,c) in triplets:
		for k in range(start/a, end/c + 1):
			extras.add((a*k, b*k, c*k))
	return {(a,b,c) for (a,b,c) in triplets|extras if a>=start and c<=end}

def is_triplet(triplet):
	(a,b,c) = sorted(triplet)
	return a ** 2 + b ** 2 == c ** 2
