from string import maketrans


def to_rna(dna):
    nucleotides = maketrans('GCTA', 'CGAU')
    return dna.translate(nucleotides)
