from string import maketrans;

def to_rna(dna):
	transtab = maketrans('GCTA', 'CGAU');
	return dna.translate(transtab);
