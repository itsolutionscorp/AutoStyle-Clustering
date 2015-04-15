def to_rna(dna):
	return ''.join(map(lambda x: dna_char_to_rna(x), dna))

def dna_char_to_rna(dna_char):
	if dna_char == 'A':
		return 'U'
	if dna_char == 'C':
		return 'G'
	if dna_char == 'G':
		return 'C'
	if dna_char == 'T':
		return 'A'
