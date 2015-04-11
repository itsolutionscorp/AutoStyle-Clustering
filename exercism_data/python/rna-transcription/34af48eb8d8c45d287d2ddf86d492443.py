''' given a DNA strand, returns its RNA complement (per RNA transcription) '''

def to_rna(dna):

	dna_to_rna = {'G': 'C', 
				  'C': 'G', 
				  'T': 'A', 
				  'A': 'U'}

	rna = []

	for nucleo in dna:
		rna.append(dna_to_rna[nucleo])

	return ''.join(rna)
