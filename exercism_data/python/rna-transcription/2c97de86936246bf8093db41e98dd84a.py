import string


def to_rna_old(dnastrand):
	'''
	Given DNA strand, return RNA strand
	by replacing each nucleotide with its complement

	>>> to_rna('UGCACCAGAAUU')
	ACGTGGTCTTAA
	'''

	mapping = {
			'G':'C',
			'C':'G',
			'T':'A',
			'A':'U'
		}
	rnastrand = ''
	for nucleotide in dnastrand:
		rnastrand += mapping[nucleotide]
	return rnastrand.upper()

def to_rna(dnastrand):
    return dnastrand.translate(string.maketrans('GCTA', 'CGAU'))
