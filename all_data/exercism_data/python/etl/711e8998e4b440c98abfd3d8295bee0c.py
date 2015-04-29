def transform(A):
	dictOut = {}
	for i in A:
		entrant = A[i]
		for j in entrant:
			dictOut[j.lower()] = i
	return dictOut
