def sieve(num):
	answers = [2]
	
	primes_dictionary = dict.fromkeys(range(3,num+1,2),True)
	
	for i in primes_dictionary:
		for j in range(3 , (i//2)+2 , 2):
			if j==i:
				continue
			elif i % j ==0:
				primes_dictionary[i] = False
		
	for key in primes_dictionary:
		if primes_dictionary[key] == True:
			answers.append(key)
				
	return (answers)
