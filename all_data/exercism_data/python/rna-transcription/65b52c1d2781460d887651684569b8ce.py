dna_complement_dict = {
		'A' : 'U',
		'C' : 'G',
		'G' : 'C', 
		'T' : 'A'
	}

def to_rna(dna_strand):

	rna_strand = []

	for protein in dna_strand:
		rna_strand.append(dna_complement_dict[protein])
	return ''.join(rna_strand)
