def hamming(A,B):
	count = 0



	for ch in range(min(len(A),len(B))):
		if A[ch] != B[ch]:
			count += 1

	count += max(len(A),len(B)) - min(len(A),len(B))

	return count
