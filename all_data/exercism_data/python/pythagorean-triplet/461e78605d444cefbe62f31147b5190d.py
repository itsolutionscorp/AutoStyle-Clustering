import math

def primitive_triplets(b):
	if b % 2 != 0:
		raise ValueError("Odd number")
	m = 2
	triplets = []
	while (2 * m <= b):
		if b / 2 % m != 0:
			m += 1
			continue
		n = b / 2 / m
		if (m > n and (m - n) % 2 != 0):
			if is_coprime(m, n):
				triplet = sorted((int(m*m - n*n), b, int(m*m + n*n)))
				triplets.append(tuple(triplet))
		m +=1
	return(set(triplets))


def is_coprime(m, n):
	for i in range(2, int(n) + 1):
		if n % i == 0 and m % i == 0:
			return(False)
	return(True)

def triplets_in_range(start, end):
	triplets = []
	for a in range(start, end + 1):
		for b in range(a, end + 1):
			c = math.sqrt(a**2 + b**2)
			if (c * 10 % 10 == 0 and
				a >= start and a <= end and
				b >= start and b <= end and
				c >= start and c <= end):
				triplets.append((a, b, int(c)))
	return(set(triplets))



def is_triplet(triplet):
	try:
		if tuple(sorted(triplet)) in primitive_triplets(triplet[0]):
			return(True)
	except:
		pass
	try:
		if tuple(sorted(triplet)) in primitive_triplets(triplet[1]):
			return(True)
	except:
		pass
	try:
		if tuple(sorted(triplet)) in primitive_triplets(triplet[2]):
			return(True)
	except:
		pass
	return(False)
