def hamming(dna1,dna2):
	hamming = abs(len(dna1) - len(dna2))
	for i,n in enumerate(dna1):
		try:
			if not n == dna2[i]:
				hamming +=1
		except IndexError:
			pass
	return hamming
