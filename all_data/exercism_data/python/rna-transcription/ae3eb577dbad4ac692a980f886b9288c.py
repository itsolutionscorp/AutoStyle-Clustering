def to_rna(dna):
	rna = ''
	for letter in dna:
		rna=rna+str(transform(letter))
	return rna

def transform(letter):
	if letter == 'G':
		return 'C'
	elif letter == 'C':
		return 'G'
	elif letter == 'T':
		return 'A'
	elif letter == 'A':
		return 'U'
