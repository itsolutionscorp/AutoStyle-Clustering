def nth_prime(number):
	primes = [2,3]
	tester=4
	while len(primes)<number:
		print len(primes)
		prime=True
		for i in range(2,(tester/2)+1):
			if tester%i==0:
				prime = False
				continue
		if prime == True:
			primes.append(tester)
		tester+=1
			
	return primes[number-1]
