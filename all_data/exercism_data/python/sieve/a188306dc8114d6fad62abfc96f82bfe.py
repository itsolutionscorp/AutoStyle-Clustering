def sieve(n):
	numbers = range(2, n + 1)
	primes = []
	for i in numbers:
		primes.append(i)
		factor =	2				
		while i * factor <= n:				
			multiple = i * factor					
			if multiple in numbers:
				numbers.remove(multiple)
			factor += 1		
	return primes
