def to_rna(dna):
	transcribe = {
		'G' : 'C',
		'C' : 'G',
		'T' : 'A',
		'A' : 'U'
	}
	return ''.join([transcribe[nucleotide] for nucleotide in dna])
