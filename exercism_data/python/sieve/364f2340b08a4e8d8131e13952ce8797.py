def sieve(num):

	nums_to_test = range(3,num+1,2)
	primes_dictionary = {}
	answers = [2]
	
	for i in nums_to_test:
		primes_dictionary[i] = True
	
	for i in nums_to_test:
		for j in range(3 , (i//2)+2 , 2):
			if j==i:
				pass
			elif i % j ==0:
				primes_dictionary[i] = False
		
	for key in primes_dictionary:
		if primes_dictionary[key] == True:
			answers.append(key)
				
	return (answers)
