def to_rna(dnaStrand):
	dna_to_rna = {'G':'C','C':'G','T':'A','A':'U'}
	rnaStrand = ''
	for nucleotide in dnaStrand:
		if nucleotide in dna_to_rna:
			rnaStrand += dna_to_rna[nucleotide]
	return rnaStrand
		
