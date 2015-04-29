# G -> C
# C -> G
# T -> A
# A -> U

def to_rna(dnaStrand):
	rnaStrand = ''
	dnaStrand.upper()
	for nucleotide in dnaStrand:
		rnaStrand += convertNucleotide(nucleotide)
	return rnaStrand
		
def convertNucleotide(nucleotide):
	return {
		'G' : 'C',
		'C' : 'G',
		'T' : 'A',
		'A' : 'U'
		}.get(nucleotide, '!')
