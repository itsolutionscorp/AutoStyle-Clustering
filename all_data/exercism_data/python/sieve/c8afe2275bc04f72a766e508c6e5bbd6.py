def sieve(n):
	numbers = range(2, n+1)
	for i in range(2, int(n**0.5)+1):
		for j in range(i**2, n+1, i): 
			if j in numbers:
				numbers.remove(j)
	
	return numbers
