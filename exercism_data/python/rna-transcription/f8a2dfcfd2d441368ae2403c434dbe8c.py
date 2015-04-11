#!/usr/bin/python

def to_rna(dna_seq):

	rna_seq = ""

	for nuc in dna_seq:
		if nuc is "G":
			rna_seq += "C"
		elif nuc is "C":
			rna_seq +="G"
		elif nuc is "T":
			rna_seq +="A"
		elif nuc is "A":
			rna_seq +="U"

	return rna_seq
