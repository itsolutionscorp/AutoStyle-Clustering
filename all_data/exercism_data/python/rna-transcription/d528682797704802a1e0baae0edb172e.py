dict_transcribes = { 'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U' }
def to_rna(dna):
	result = ''
	for neuclotides in dna:
		result += dict_transcribes[ neuclotides ]
	return result
