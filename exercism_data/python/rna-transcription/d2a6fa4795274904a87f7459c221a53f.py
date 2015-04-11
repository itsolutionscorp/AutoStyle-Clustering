# -*- coding: utf-8 -*-
'''
RNA Transcription.

Author: SprayIdle

This program, given a DNA strand, returns its RNA complement (per RNA 
transcription).

TESTING:

	rna_transcription_test.py (seperate file) will run numerous test cases
	over the dna module.
	
REVISION HISTORY:

	24/09/14:	Initial implementation of to_rna function.
'''
rna_complement = {
	'G': 'C',
	'C': 'G',
	'T': 'A',
	'A': 'U',
	}

def to_rna(dna):
	'''(sting) -> string
	
	Return a string representing the RNA nucleotide complement of a given DNA
	nucleotide string.
	'''
	rna = ''
	for nucleotide in dna:
		rna += rna_complement[nucleotide]
	return rna
