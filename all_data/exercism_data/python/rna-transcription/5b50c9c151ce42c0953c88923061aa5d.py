from string import maketrans

trans_tab = maketrans("GCTA", "CGAU")

def to_rna(dna):
	return dna.translate(trans_tab);
