from string import maketrans

def to_rna(dna):
	rna_transcription = maketrans("GCTA", "CGAU")
	return dna.translate(rna_transcription)
