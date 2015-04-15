def to_rna(dnastrand):
	'''
	Given DNA strand, return RNA strand
	by replacing each nucleotide with its complement

	>>> to_rna('UGCACCAGAAUU')
	ACGTGGTCTTAA
	'''

	mapping = {
			'G':'c',
			'C':'g',
			'T':'a',
			'A':'u'
		}
	rnastrand = ''
	for nucleotide in dnastrand:
		rnastrand += mapping[nucleotide]
	return rnastrand.upper()
