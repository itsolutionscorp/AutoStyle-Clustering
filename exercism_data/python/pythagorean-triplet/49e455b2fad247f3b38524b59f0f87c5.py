import itertools

def is_triplet(liste):
	right=max(liste)**2
	left = sum([x**2 for x in liste if x!=max(liste)])
	return left==right
	
def are_coprime(m,n):
	h = [y for y in range(2,m) if m % y ==0 ]
	k = [y for y in range(2,n) if n % y ==0 ]
	summe = 0
	for x in h:
		if x in k:
			summe +=1
	if summe == 0:
		return True
	else:
		return False 
		
def primitive_triplets(b):
	if b%4!=0:
		raise ValueError
	else:
		l = [[m,n] for m,n in itertools.product(range(1,b+1),range(1,b+1)) if ((2*m*n==b) & (are_coprime(m,n)==True) & (m > n))]
		a = [listen[0]**2 - listen[1]**2 for listen in l]
		c = [listen[0]**2 + listen[1]**2 for listen in l]
		bb= [b for i in range(len(c))]
		z = 0
		for i in range(0,len(a)):
			if (are_coprime(a[i-z],bb[i-z])==False) or (are_coprime(a[i-z],c[i-z])==False):
				del a[i-z]
				del c[i-z]
				del bb[i-z]
				z+=1
		for i in range(0,len(a)):
			if a[i] > bb[i]:
				z = a[i]
				a[i] = bb[i]
				bb[i] = z
		return set(zip(a,bb,c))


def triplets_in_range(mi,ma):
	return {tuple([a,b,c]) for a,b,c in itertools.product(range(mi,ma+1),range(mi,ma+1),range(mi,ma+1)) if ((a**2 +b**2 ==c**2) & (b > a))}
