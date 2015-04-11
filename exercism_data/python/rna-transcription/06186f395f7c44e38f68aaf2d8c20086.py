import string

def to_rna(dna):
    comp = string.maketrans('GCTA', 'CGAU')
    return dna.translate(comp)
