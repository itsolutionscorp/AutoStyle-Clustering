def hamming(dna1,dna2):
	dna1 = dna1.upper()
	dna2 = dna2.upper()	
	
	count = 0
	long_dna = max(dna1,dna2,key=len)
	for i in range(len(long_dna)):
		try:
			if (dna1[i] != dna2[i]):
				count = count + 1
		except IndexError:
			count = count + 1
	return count
