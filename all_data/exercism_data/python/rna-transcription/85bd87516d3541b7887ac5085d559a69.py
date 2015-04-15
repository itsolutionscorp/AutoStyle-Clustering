def translate(n):
	if n == 'C' : return 'G'
	if n == 'G' : return 'C'
	if n == 'A' : return 'U'
	if n == 'U' : return 'A'
	if n == 'T' : return 'A'

def to_rna(dna):
	return ''.join(list(map(translate, dna)))
