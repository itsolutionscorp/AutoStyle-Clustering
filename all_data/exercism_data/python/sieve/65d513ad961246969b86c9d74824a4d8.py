def sieve(n):
	ret = [True for i in range(n)]
	cutoff = int(n**.5)+1
	for i in range(2,cutoff):
		if ret[i] == True:
			k = 2
			j = i**k
			while j <= n-1:
				ret[j] = False
				k += 1
				j = i * k
	return [i for i in range(2,n) if ret[i] != False]
