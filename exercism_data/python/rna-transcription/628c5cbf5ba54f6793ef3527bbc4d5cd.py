def to_rna(dna):
		return ''.join([_dna_to_rna_dict[nucleotide] for nucleotide in dna])

_dna_to_rna_dict = {'G': 'C',
					'C': 'G',
					'T': 'A',
					'A': 'U'}
