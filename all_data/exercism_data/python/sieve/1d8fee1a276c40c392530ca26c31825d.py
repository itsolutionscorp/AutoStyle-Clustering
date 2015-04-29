def sieve(n):
	r = range(2, n + 1)
	i = 0
	while i < len(r):
		j = i + 1
		while j < len(r):
			if r[j] % r[i] == 0:
				r.pop(j)
				break
			else:
				j += 1
		else:
			i += 1
	
	return r
