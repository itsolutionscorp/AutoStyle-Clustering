from string import maketrans


def to_rna(dna):
    pairs = maketrans('CGTA', 'GCAU')
    return dna.translate(pairs)
