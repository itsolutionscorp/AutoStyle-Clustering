def to_rna(dna_sequence):
	transcription = {'G':'C',
					'C':'G',
					'T':'A',
					'A':'U'}

	rna_sequence = ''
	for i in dna_sequence:
		rna_sequence += transcription[i]

	return rna_sequence
