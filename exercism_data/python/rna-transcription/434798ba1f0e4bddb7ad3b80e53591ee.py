def to_rna(dna):
	complement = {'G':'C','C':'G','T':'A','A':'U'};
	return ''.join(complement[m] if m in complement else 'X' for m in dna);
