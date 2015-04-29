import string


def to_rna(dna_string):
    table = string.maketrans("GCTA", "CGAU")
    return dna_string.translate(table)
