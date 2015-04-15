def to_rna(letters):
	from string import maketrans
	tranTab = maketrans('GCTA', 'CGAU')
	
	return letters.translate(tranTab)
