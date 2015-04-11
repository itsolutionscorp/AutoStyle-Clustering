import string


def to_rna(dna):
    mapping = string.maketrans('GCTA','CGAU')
    return dna.translate(mapping)
