from fractions import gcd

def is_triplet(triplet):
	if len(triplet) != 3:
		raise ValueError('Triplets consist of three values, must pass exactly 3 numbers.')
	else:
		a,b,c = sorted(triplet)

		return a*a + b*b == c*c

def primitive_triplets(b):
	if b%4:
		raise ValueError

	mn = b/2
	n = 1
	triplets = set()
	while n<mn**.5:
		if not mn%n:
			m = mn/n
			if gcd(m, n) == 1:
				a = m*m - n*n
				c = m*m + n*n
				triplets.add(tuple(sorted([a,b,c])))

		n+=1

	return triplets

def triplets_in_range(min_ran, max_ran):
	triplets = set()
	for x in range(min_ran, max_ran + 1):
		y = x + 1
		z = y + 1
		while z <= max_ran:
			while z*z < x*x + y*y:
				z += 1

			if z*z == x*x + y*y:
				triplets.add((x,y,z))

			y += 1

	return triplets
