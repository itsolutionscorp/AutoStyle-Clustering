def is_triplet(triplet):
	a, b, c = sorted(triplet)
	return a**2 + b**2 == c**2

def primitive_triplets(b):
	if b % 4 != 0:
		raise ValueError
	triplets = set()
	halfb = b // 2
	for n in range(1, halfb):
		for m in range(n + 1, halfb + 1, 2):
			if m * n == halfb:
				triplets.add(tuple(sorted((m**2+n**2, b, m**2-n**2))))
	return triplets

def triplets_in_range(rmin, rmax):
	triplets = set()
	for a in range(rmin, rmax - 1):
		for b in range(a + 1, rmax):
			for c in range(b + 1, rmax + 1):
				triplet = (a, b, c)
				if is_triplet(triplet):
					triplets.add(triplet)
	return triplets
