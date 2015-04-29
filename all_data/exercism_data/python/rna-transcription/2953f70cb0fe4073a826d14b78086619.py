# -*- coding: utf-8 -*-


def to_rna(strand):
	rna = ''
	for nucleotide in strand:
		rna += _convert_dna(nucleotide)
	return rna

def _convert_dna(nucleotide):
	dna_nucleotides = {'C': 'G', 'G': 'C', 'A': 'U', 'T': 'A'}
	if nucleotide in dna_nucleotides:
		return dna_nucleotides[nucleotide]
	return ''
