def prime_factors(n):
	fl = []
	while not n & 1:
		fl.append(2)
		n >>= 1
	f = 3
	while n > 1 and f < n:
		while not n % f:
			fl.append(f)
			n /= f
		f += 2
	if n > 1:
		fl.append(n)
	return fl
