def to_rna(genes):
	codec={'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	result=""
	for i in range(len(genes)):
		result+=(codec[genes[i]])
	return result
