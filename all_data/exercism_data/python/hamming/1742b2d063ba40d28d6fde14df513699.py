def distance(a,b):
	hamming = 0
	for nucleotide in range(len(a)):
		if a[nucleotide] != b[nucleotide]:
			hamming += 1
	return hamming
