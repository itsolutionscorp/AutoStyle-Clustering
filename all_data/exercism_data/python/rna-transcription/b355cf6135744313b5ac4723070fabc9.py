def to_rna(dna=''):
	"""
	Translates dna strings to rna strings.
	"""
	translate_dict = str.maketrans('GCTA', 'CGAU')
	rna = dna.translate(translate_dict)
	return rna
