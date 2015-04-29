def count(dna, nuc):
	if len(dna) != 0 and not nuc in dna:
		raise ValueError( "{}: not match".format(nuc) )
	return len( dna.split(nuc) ) - 1

def nucleotide_counts(dna):
	result = {'A': 0, 'T': 0, 'C': 0, 'G': 0}
	for item in dna:
		if item in result:
			result[item] += 1

	return result

print nucleotide_counts("AADFDFT")
