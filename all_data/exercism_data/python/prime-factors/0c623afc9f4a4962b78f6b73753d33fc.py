def prime_factors(n):
	factors = []
	num = 2
	while num**2 <=n:
		while (n%num) == 0:
			factors.append(num)
			n /= num
		num+=1
	if n > 1:
		factors.append(n)
	return factors
