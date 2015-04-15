def to_rna(strand):
	rna = ''
	for i in strand:
		rna += translate(i)
	return rna

def translate(base):
	if base == 'C':
		return 'G'
	elif base == 'G':
		return 'C'
	elif base == 'A':
		return 'U'
	elif base == 'T':
		return 'A'
