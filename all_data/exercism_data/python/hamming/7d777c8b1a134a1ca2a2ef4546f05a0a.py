def hamming(A, B):
	#Create a counter for the hams that happen
	hamCount = 0

	#Find which one is longer string
	if len(A) > len(B):
		j = len(B) 
		
		difference = len(A) - len(B)
	else:
		j = len(A) 
		difference = len(B) - len(A)
	
	for i in range(0, j):
		
		if A[i] != B[i]:
			hamCount += 1
		

	hamCount = hamCount + difference
	return hamCount
