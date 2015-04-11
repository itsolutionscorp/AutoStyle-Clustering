def distance(A, B):
	dist = 0
	for x in range(0,len(A)):
		if A[x] != B[x]:
			dist = dist + 1
	return dist
