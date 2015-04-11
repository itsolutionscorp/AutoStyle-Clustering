def to_rna(strand):
	strand = strand.replace('G', 'c')
	strand = strand.replace('C', 'g')
	strand = strand.replace('T', 'a')
	strand = strand.replace('A', 'u')
	return  strand.upper()
