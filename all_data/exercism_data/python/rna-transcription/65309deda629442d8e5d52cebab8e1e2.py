import string


def to_rna(helix):
    return helix.translate(string.maketrans('GCTA', 'CGAU'))
