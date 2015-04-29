# DNA->RNA transcription

from string import maketrans

def to_rna(code):
	dna = "GCTA"
	rna = "CGAU"
	translation_table = maketrans(dna, rna)
	return code.translate(translation_table)
