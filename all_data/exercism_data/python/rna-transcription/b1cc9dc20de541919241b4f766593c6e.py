mappings = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(strand):
	result_list = []
	for char in strand:
		result_list.append(mappings[char])

	return ''.join(result_list)
