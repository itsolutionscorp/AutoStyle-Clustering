from itertools import combinations
from math import ceil, sqrt

# calculates the greatest common divisor of all the provided arguments
def gcd(*args):
	if len(args)==0:
		return 1
	elif len(args)==1:
		return args[0]
	elif len(args)==2:
		a,b=args
		while b:
			a,b=b,a%b
		return a
	else:
		answer=args[0]
		for a in args[1:]:
			answer=gcd(answer,a)
		return answer

# calculates all ways to write n as the product of two positive coprime numbers
# the return value will be sorted based on the first element of each pair
def _factor_pairs(n):
	answer=[]
	for i in range(1,ceil(sqrt(n))):
		if n%i==0 and gcd(i,n//i)==1:
			answer.append((i,n//i))
	return answer

# determines whether or not the provided numbers form a Pythagorean triplet
def is_triplet(p):
	a,b,c=p
	return a**2+b**2==c**2 or a**2+c**2==b**2 or b**2+c**2==a**2

# calculates all Pythagorean triplets whose members all fall between x and y (inclusive)
def triplets_in_range(x,y):
	answer=set()
	for a,b,c in combinations(range(x,y+1),3):
		if a**2+b**2==c**2:
			answer.add((a,b,c))
	return answer

# calculates all primitive Pythagorean triplets which have b as an element
# b must be divisible by 4
def primitive_triplets(b):
	if b%4!=0:
		raise ValueError("%s is not a multiple of 4."%b)
	answer=set()
	for n,m in _factor_pairs(b//2):
		answer.add(tuple(sorted((m**2-n**2,b,m**2+n**2))))
	return answer
