#!/usr/bin/python -tt

def distance(dna1,dna2):
	out = 0
	
	if len(dna1) != len(dna2):
		raise SyntaxError('Different length')
		
	for i in range(len(dna1)):
		if dna1[i] != dna2[i]:
			out+=1
			
	return out
