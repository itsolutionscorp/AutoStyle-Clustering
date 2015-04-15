#!/usr/local/bin/python

def to_rna(dna_strand):
	rna_sequence = {'G':'C','C':'G','T':'A','A':'U'}
	rna_complement = ""
	for x in dna_strand:
		rna_complement = rna_complement + rna_sequence[x]
	return rna_complement

