import string


def to_rna(dna):
    return dna.translate(string.maketrans('GCTA', 'CGAU'))
