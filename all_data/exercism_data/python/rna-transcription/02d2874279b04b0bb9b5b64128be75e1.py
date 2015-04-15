#!/usr/bin/python -tt

def to_rna(dna_string):
	out=''
	for c in dna_string:
		if c == 'G':
			out+='C'
		elif c == 'C':
			out+='G'
		elif c == 'T':
			out+='A'
		elif c == 'A':
			out+='U'
		else:
			raise('Incorrect DNA sequence')
	return out
