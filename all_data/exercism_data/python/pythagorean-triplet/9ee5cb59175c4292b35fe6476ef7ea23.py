import itertools



	
def coprime(m,n):
	x = [m,n]
	while x[0] != x[1]:
		x.sort()
		x = [x[0],x[1]-x[0]]
	if x[0] == 1:
		return True
	return False
		


def get_mn(x):
	testers = []
	res = []
	for m in range(2,x+1):
		if x%m==0:
			n=x/m
			testers.append((m,n))
	for pair in testers:
		m,n = pair
		if m>n and (m-n)%2==1:
			if coprime(m,n):
				res.append((m,n))
	return res


def is_triplet(trips):
	a,b,c = trips
	return a**2+b**2==c**2 or (a**2+c**2==b**2 or c**2+b**2==a**2)
	
	
def primitive_triplets(b):
	if b%4 != 0:
		raise ValueError('b must be divisible by 4!')
	testers = get_mn(b/2)
	results = []
	for pair in testers:
		m,n = pair
		a=((m**2)-(n**2))
		c=((m**2)+(n**2))
		temp = [a,b,c]
		temp.sort()
		results.append((temp[0],temp[1],temp[2]))
	return set(results)

def triplets_in_range(min, max):
	numbers = [i for i in range(min,max+1)]
	results = []
	for x in itertools.combinations(numbers,3):
		if is_triplet(x):
			results.append(x)
	return set(results)




def readme_exercise():
	x = triplets_in_range(1,500)
	for i in x:
		if sum(i) == 1000:
			print i
			print i[0]*i[1]*i[2]
			return i[0]*i[1]*i[2]
