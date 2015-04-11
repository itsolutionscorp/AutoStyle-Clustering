#!/usr/bin/python -tt

def to_rna(dna_string):
	out=''
	
	transc = {"G":"C", "C":"G", "T":"A", "A":"U"}
	
	for c in dna_string:
		out+=transc[c]
	return out
