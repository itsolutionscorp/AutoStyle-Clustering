import re, string

def to_rna(dna):
	# Input validation
	if not re.match('^[ACGT]+$', dna):
		raise ValueError("Input is not a valid DNA strand")

	tr = string.maketrans("GCTA", "CGAU")
	return dna.translate(tr)
