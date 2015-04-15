conv = {
		#DNA -> RNA
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U',
}
def convert(letter):
	return conv[letter]

def to_rna(dna):
	return ''.join(map(convert, list(dna)))
