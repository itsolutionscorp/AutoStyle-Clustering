def hamming(strand1, strand2):
	length = len(strand1) if len(strand1) > len(strand2) else len(strand2)
	hamming = 0
	for nucleotide in range(length):
		try:
			if strand1[nucleotide] != strand2[nucleotide]:
				hamming = hamming+1
		except IndexError:
				hamming = hamming+1



	return hamming
