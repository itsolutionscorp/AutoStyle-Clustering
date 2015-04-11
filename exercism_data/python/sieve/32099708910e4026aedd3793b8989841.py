def sieve(n):
	numbers = range(2, n+1)
	for i in numbers:
		for j in range(i, numbers[-1]/i + 1): 
			if (j * i) in numbers:
				numbers.remove(j*i)
				
	return numbers
