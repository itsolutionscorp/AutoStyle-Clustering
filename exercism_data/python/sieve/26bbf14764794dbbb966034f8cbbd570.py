def sieve(l):
	a = [ True for x in range(l+1) ]
	a[0] = a[1] = False

	for i in range(2, int(l ** 0.5) + 1):
		if a[i] == True:
			for j in range(i*i, l + 1, i):
				a[j] = False
	la = []
	for i in range(l + 1):
		if a[i] == True:
			la.append(i)
	return la
