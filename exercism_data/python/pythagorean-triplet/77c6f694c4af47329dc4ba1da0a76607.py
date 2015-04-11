from math import sqrt
def is_coprime(m, n):
	def gcd(a,b):
		return a if b == 0 else gcd(b, a % b)
	return gcd(m,n) == 1

def primitive_triplets(b):
	if b % 4 != 0:
		raise ValueError

	triplets = set()
	mn = b / 2

	for n in range(1, mn):
		m = mn / n
		if (m-n) > 0 and (m-n) % 2 != 0 and is_coprime(m,n):
			a = m*m - n*n
			c = m*m + n*n
			if sqrt(a*a + b*b) == c:
				triplet = tuple(sorted([a,b,c])) 
				triplets.add(triplet)
	return triplets


def triplets_in_range(m, n):
	triplets = set()

	for a in range(m, n+1):
		for b in range(a+1, n+1):
			for c in range(b+1, n+1):
				if is_triplet((a,b,c)):
					triplets.add((a,b,c))
	return triplets


def is_triplet(triplet):
	a,b,c = sorted(triplet)
	return a*a + b*b == c*c
