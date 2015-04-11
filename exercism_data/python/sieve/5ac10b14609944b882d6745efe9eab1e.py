def sieve(lim):
	r = range(2, lim + 1)

	for i in r: # Test each number in r
		print "Test value:", i
		if i**2 < lim:
			prime = True # condition is true by default
			for x in range(1, i): # test if number is prime
				if i % x == 0:
					if x != i and x != 1:
						prime = False # condition = False
						break
			if prime:
				for z in r:
					if z > i and (z % i == 0): 
						try:
							r.remove(z)
							#print "removed:", z
						except ValueError:
							pass
	return r

if __name__ == '__main__':
	a = sieve(10)
	print len(a)
	print a
