#dna string manipulations

def to_rna(dna):
	output = ''
	reference = {'A':'U', 'T':'A', 'C':'G', 'G':'C'}
	for c in dna:
		output += reference[c]
	return output
