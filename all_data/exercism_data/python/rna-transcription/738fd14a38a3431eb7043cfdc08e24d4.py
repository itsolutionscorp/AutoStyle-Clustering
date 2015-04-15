dna_to_rna_map = {
	'G': 'C',
	'C': 'G',
	'T': 'A',
	'A': 'U',
}

def to_rna(word):
	return "".join([dna_to_rna_map[letter] for letter in word])
