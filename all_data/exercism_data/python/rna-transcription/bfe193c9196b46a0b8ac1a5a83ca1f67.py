def to_rna(dna):
	transcription = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U'
	}
	rna = ''

	if len(dna) == 1:
		rna = transcription[dna]

	else:
		for nuc in dna:
			rna += transcription[nuc]

	return rna
