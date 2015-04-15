def to_rna(s):
	return s.replace('G', 'c').replace('C', 'g').replace('T', 'a').replace('A', 'u').upper()
