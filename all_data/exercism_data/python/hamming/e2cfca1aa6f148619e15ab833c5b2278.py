#
# Skeleton file for the Python "Hamming" exercise.
#

#I don't know why the test adds the diff between the arg's

def hamming(dna_1, dna_2):
	d = abs(len(dna_1) - len(dna_2))
	for i in min(range(len(dna_1)), range(len(dna_2))):
		if dna_1[i] != dna_2[i]:
			d += 1
	return d
