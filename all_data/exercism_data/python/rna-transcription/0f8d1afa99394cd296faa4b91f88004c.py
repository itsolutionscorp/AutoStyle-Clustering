from string import maketrans

def to_rna(dna):
	dna_nucleotides = "GCTA"
	rna_nucleotides = "CGAU"
	translations = maketrans(dna_nucleotides, rna_nucleotides)
	return dna.translate(translations)
