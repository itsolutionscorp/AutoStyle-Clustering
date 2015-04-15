def to_rna(nucleotide):
	translation_dict = {'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}
	nucleotide_list = list(nucleotide)
	converted_rna = []
	for nucleo in nucleotide_list:
		converted_rna.append(translation_dict[nucleo])

	return ''.join(converted_rna)
