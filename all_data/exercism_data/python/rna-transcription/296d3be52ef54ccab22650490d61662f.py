def to_rna(signs):
	rna_dict = {'G':'C',
				'C':'G',
				'T':'A',
				'A':'U'}
	resulting_str = ""
	for sign in signs:
		resulting_str += rna_dict[sign]
	return resulting_str
