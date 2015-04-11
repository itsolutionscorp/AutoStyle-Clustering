

def rna_transciption(sequence):
	basecomplement = {'A': 'U', 'C': 'G', 'G': 'C', 'T': 'A'}
	letters = list(sequence)
	letters = [basecomplement[base] for base in letters]
	return ''.join(letters)
