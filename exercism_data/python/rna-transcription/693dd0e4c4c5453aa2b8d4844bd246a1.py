def to_rna(dna_sample):
	sequence = {'G': 'C','C': 'G', 'T':'A', 'A': 'U'}
	where = 0
	lis = []
	for x in range(0,len(dna_sample)):
		lis.append(sequence[dna_sample[where]]) 
		where += 1
	return "".join(lis)
