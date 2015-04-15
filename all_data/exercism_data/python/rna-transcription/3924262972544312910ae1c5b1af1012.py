mappings = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(strand):
	return ''.join(mappings[char] for char in strand)
