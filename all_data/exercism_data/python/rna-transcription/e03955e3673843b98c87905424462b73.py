from string import maketrans

trans_table = maketrans("GCTA", "CGAU")

def to_rna(dna_strand):
    return dna_strand.translate(trans_table)
