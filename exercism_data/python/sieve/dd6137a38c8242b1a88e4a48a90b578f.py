def sieve(upperlimit):
	numberlist = []
	for x in xrange(2,upperlimit+1):
		numberlist.append(x)
	for prime in numberlist:
		if prime != -1:
			print prime
			for x in xrange(prime,len(numberlist)):
				if (numberlist[x]%prime == 0):
					numberlist[x] = -1
	while(1):
		try:
			numberlist.remove(-1)
		except:
			break
	return numberlist
