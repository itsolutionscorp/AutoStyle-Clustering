import string


def to_rna(dna):
    rna_table = string.maketrans("GCTA", "CGAU")
    return dna.translate(rna_table)
