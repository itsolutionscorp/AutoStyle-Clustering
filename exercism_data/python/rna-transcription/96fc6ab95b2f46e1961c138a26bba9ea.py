import string


def to_rna(dna):
	transcription = string.maketrans('GCTA', 'CGAU')
	return string.translate(dna, transcription)
