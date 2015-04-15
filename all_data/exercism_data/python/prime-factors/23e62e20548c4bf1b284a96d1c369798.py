def sieve(n):
	sublim = int(n**0.5)
	flags = [0,0] + [1]*(n-2)
	yield 2
	for x in range(3,n,2):
		if not flags[x]:
			continue
		yield x
		if x <= sublim:
			for y in range(x,n,x<<1):
				flags[y] = 0

def is_prime(n):
	return n in (2, 3)\
		or not any(n%p==0 for p in sieve(int(n**0.5) + 1))

def prime_factors(n):
	retlist = []
	if n == 1:
		return retlist
	while not is_prime(n):
		for p in sieve(int(n**0.5)+1):
			if not n%p:
				n /= p
				retlist.append(p)
				break
	retlist.append(n)
	return retlist
