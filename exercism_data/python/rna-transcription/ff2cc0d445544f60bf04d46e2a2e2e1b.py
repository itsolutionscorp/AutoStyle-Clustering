#
# Skeleton file for the Python "RNA" exercise.
#

def to_rna(dna):
	rna_a = dna.replace('A', 'U')
	rna_t = rna_a.replace('T', 'A')
	rna_c_1 = rna_t.replace('C', '1')
	rna_g = rna_c_1.replace('G', 'C')
	rna_c = rna_g.replace('1', 'G')
	return rna_c
