
def to_rna(dna):
	maps = {
		'A': 'U',
		'T': 'A',
		'G': 'C',
		'C': 'G'
	}
	rna = ''
	for nucleotide in dna:
		if nucleotide in maps:
			rna = rna + maps[nucleotide]
	return rna
