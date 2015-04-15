# Finds the number of mistakes between dna & rna.
def distance(dna, rna):
	
	count = 0
	for i, j in zip(dna,  rna):
		if i != j:
			count += 1
	return count
