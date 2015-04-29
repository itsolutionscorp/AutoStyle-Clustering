dna_to_rna = {
	'G':'C',
	'C':'G',
	'T':'A',
	'A':'U',	
}

def to_rna(dna):
	return "".join([dna_to_rna[item] for item in dna])
