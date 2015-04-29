import string

def to_rna(dna):
    trans = string.maketrans('GCTA', 'CGAU')
    return dna.translate(trans)
