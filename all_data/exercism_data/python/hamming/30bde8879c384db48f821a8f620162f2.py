def distance(first, second):
	hamming = 0
	for n in range(len(first)):
		if (first[n] != second[n]):
			hamming = hamming + 1
			
	return hamming
