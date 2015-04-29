def sieve(limit):
	marker =[]
	numbers=[]
	prime=[2]
	for i in range(2,limit):
		marker.append(0)
		numbers.append(i)
		
	for i in range(0,len(numbers)):
		if numbers[i] % 2 ==0:
			marker[i] =1
	for i in range(1,len(numbers)):
		if marker[i] == 0:
			z = 1
			for k in range(0,len(prime)):
				if numbers[i] % prime[k] ==0:
					z=0
			if z ==1:
				prime.append(numbers[i])
				for k in range(i,len(numbers)):
					if numbers[k] % numbers[i] ==0:
						marker[k]=1
	return prime
					
			
		
