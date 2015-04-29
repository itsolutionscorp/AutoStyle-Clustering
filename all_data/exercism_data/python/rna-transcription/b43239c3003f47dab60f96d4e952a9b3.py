#!/usr/bin/env python
def to_rna(dna_sequence):
	rna_sequence = ''
	for x in range(len(dna_sequence)):
		nucleotide = dna_sequence[x]
		if nucleotide == 'A':
			rna_sequence += 'U'
		elif nucleotide == 'T':
			rna_sequence += 'A'
		elif nucleotide == 'G':
			rna_sequence += 'C'
		elif nucleotide == 'C':
			rna_sequence += 'G'
	return rna_sequence
