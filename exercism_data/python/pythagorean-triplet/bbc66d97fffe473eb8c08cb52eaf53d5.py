import math

def primitive_triplets(b):
	if b%4 != 0:
		raise ValueError('b must be divisible by 4')
	retval = []
	for m in range(math.ceil((b/2)**0.5),1+int(b/2)):
		n = b/(2*m)
		if (n%1==0):
			n = int(n)
			if _gcd(m,n)==1:
				retval += [tuple(sorted([m**2-n**2, b, m**2+n**2]))]
	return set(retval)

def _gcd(m,n):
	'''Euclidean algorithm to check if m and n are coprime'''
	while m:
		m,n = n%m, m
	return n


def triplets_in_range(low,high):
	retval = []
	for a in range(low,high):
		for b in range(a+1,high):
			for c in range(b+1,high+1):
				if is_triplet((a,b,c)):
					retval += [(a,b,c)]
	return set(retval)

def is_triplet(coefs):
	a,b,c = sorted(coefs)
	return (a**2+b**2==c**2)


# A method for finding all primitive pythagorean triplet is given in wikipedia
# (http://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple). The
# triplet a=(m^2-n^2), b=2*m*n and c=(m^2+n^2), where m and n are coprime and
# m-n>0 is odd, generate a primitive triplet. Note that this implies that b has
# to be divisible by 4 and a and c are odd. Also note that we may have either
# a>b or b>a.
#
# The function primitive_triplets should then use the formula above with b set
# to its argument and find all possible pairs (m,n) such that m>n, m-n is odd,
# b=2*m*n and m and n are coprime.
#

if __name__=='__main__':
	primitive_triplets(84)
	triplets_in_range(56, 95)
	is_triplet((29, 20, 21))
