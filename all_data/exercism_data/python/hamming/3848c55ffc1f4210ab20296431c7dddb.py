def hamming(dna1, dna2):
	dna1_size = len(dna1)
	dna2_size = len(dna2)
	shorter = dna1
	longer = dna2
	if(len(dna2) < len(dna1)):
		shorter = dna2
		longer = dna1
	ham = len(longer)-len(shorter)

	for i in range(len(shorter)):
		if shorter[i] is not longer[i]:
			ham +=1

	return ham

print hamming("ABCDEFG", "BBCDEFGHI")
