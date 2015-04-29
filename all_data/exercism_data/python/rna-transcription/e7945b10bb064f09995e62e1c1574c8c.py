from string import maketrans, translate

dna = 'GCTA'
rna = 'CGAU'

dna_to_rna_translation = maketrans(dna, rna)

to_rna = lambda dna: translate(dna, dna_to_rna_translation)
