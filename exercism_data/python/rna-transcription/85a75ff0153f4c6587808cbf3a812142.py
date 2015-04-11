def to_rna(letters):
	translate = {'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}
	final = ""
	for i in range(len(letters)):
		final += translate[letters[i]]
	return final
