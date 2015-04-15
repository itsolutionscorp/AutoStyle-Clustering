import re

def to_rna(dna):
	# Input validation
	if not re.match('^[ACGT]+$', dna):
		raise ValueError("Input is not a valid DNA strand")

	rna = ""
	for n in dna:
		if n == 'G':
			rna += 'C'
		elif n == 'C':
			rna += 'G'
		elif n == 'T':
			rna += 'A'
		elif n == 'A':
			rna += 'U'

	return rna
