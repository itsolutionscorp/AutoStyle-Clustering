def sieve(n):
	result = range(2, n + 1)
	i = 0
	p = result[i]
	while p * p <= n:
		result = [res for res in result if res == p or res % p != 0]
		i += 1
		p = result[i]
	return result

		
