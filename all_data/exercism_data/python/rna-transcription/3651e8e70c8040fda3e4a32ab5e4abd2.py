complement = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}



def to_rna(dna):
	if len(dna) > 1:
		dna
	# have to figure out how to pass the multiple conversions test (ACGTGGTCTTAA)
	return complement[dna]
