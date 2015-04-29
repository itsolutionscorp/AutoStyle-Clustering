def sieve(n):
	sublim = int(n**0.5)
	flags = [0,0] + [1]*(n-2)
	ret = [2]
	for x in range(3,n,2):
		if not flags[x]:
			continue
		ret.append(x)
		if x <= sublim:
			for y in range(x,n,x<<1):
				flags[y] = 0
	return ret
