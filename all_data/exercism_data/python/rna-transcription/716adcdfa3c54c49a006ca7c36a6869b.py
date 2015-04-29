def to_rna(strand):
	pairs = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	sequence = list(strand)
	convert = []
	for nuc in sequence:
		convert.append((pairs[nuc]))
	return ''.join(convert)
		
	
