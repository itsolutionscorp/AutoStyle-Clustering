import string

def to_rna(dna):
	# Script that returns the RNA complement of a given DNA strand

	rna = '' 
	reverse_complement = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

	for i in range(len(dna)):
		rna += reverse_complement[dna[i]]

	return rna
