import math
# Math is hard.  Let's go shopping!

def is_triplet(abc):
	a,b,c = sorted(abc)
	return a * a + b * b == c * c

def triplets_in_range(low, high):
	res = set()
	for a in xrange(low, high + 1):
		for b in xrange(low, a + 1):
			c2 = a * a + b * b
			cc = int(math.sqrt(c2))
			if cc * cc == c2 and cc <= high:
				res.add((b, a, cc))
	return res

def gcd(a, b):
	if a < b:
		a, b = b, a # ensure b <= a
	return a if b == 0 else gcd(b, a % b)

def primitive_triplets(b):
	res = set()
	if b % 2 == 1:
		raise ValueError("No such triplets")
	b2  = b / 2
	for m in xrange(int(math.sqrt(b2)), b2 + 1):
		if b2 % m == 0:
			n = b2 / m
			diff = m - n
			if diff <= 0 or diff % 2 == 0 or gcd(m, n) != 1:
				continue
			res.add(tuple(sorted([m * m - n * n, b, m * m + n * n])))
	if len(res) == 0:
		raise ValueError("No such triplets")
	return res
