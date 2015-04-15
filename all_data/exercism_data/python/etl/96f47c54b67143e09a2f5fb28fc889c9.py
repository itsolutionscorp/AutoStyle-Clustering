def transform(A):
	dictOut = {}
	for i in A:
		for j in A[i]:
			dictOut[j.lower()] = i
	return dictOut
