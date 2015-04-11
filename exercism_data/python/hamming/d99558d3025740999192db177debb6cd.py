def hamming(dna1,dna2):
	hamming = 0;
	sdna = min(dna1,dna2, key=len);
	ldna = max(dna1,dna2, key=len)
	for i, c in enumerate(sdna):
		if dna1[i] != dna2[i]:
			hamming +=1;
	hamming += len(ldna)-len(sdna);
	return hamming;
