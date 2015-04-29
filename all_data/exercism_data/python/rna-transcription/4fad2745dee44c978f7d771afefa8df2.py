import string


def to_rna(dna):
    table = string.maketrans('GCTA', "CGAU")
    return string.translate(dna, table)
