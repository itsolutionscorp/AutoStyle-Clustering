def sieve (number):
	
	prime_list = []
	for n in range(2, number):
		prime = True
		for i in range(2, n/2+1):
			if n % i == 0:
				prime = False
		if prime == True:
			prime_list.append(n)
			
	return prime_list
