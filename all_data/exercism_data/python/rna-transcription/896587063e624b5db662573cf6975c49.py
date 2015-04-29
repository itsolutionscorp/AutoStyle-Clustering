def to_rna(strand):
	return 	strand.replace('C', '%1%').replace('G', 'C').replace('%1%', 'G').replace('A', 'U').replace('T', 'A')
