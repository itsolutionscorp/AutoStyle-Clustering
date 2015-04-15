import string


TRANS = string.maketrans('GCTA', 'CGAU')

def to_rna(strand):
    return string.translate(strand, TRANS)
