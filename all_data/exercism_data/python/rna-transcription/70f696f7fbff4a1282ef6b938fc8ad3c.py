import string

def to_rna(sequence):
    return string.translate(sequence, string.maketrans('GCTA', 'CGAU'))
