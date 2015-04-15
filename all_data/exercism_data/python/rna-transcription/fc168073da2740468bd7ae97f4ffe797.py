from string import maketrans

translation = maketrans('GCTA', 'CGAU')


def to_rna(dna):
    return dna.translate(translation)
