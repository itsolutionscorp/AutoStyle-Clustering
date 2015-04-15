import itertools

def sieve(limit):
	x = list(range(2,limit))
	y = 0
	for i in range(len(x)):
		y = x[0]
		print y
		print x[i]
		if x[i]%y==0:
			x.remove(x[i])
		print x
