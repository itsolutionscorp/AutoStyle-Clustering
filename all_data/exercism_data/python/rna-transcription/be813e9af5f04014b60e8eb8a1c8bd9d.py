def to_rna(strand):

	strand = strand.replace('A','U')
	strand = strand.replace('T','A')
	# Temporarily replace 'G' chars with 'Z'
	strand = strand.replace('G','Z')
	strand = strand.replace('C','G')
	strand = strand.replace('Z','C')

	return strand
