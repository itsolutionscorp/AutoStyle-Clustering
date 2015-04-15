import string

def to_rna(dna):
    trans_table = string.maketrans('GCTA', 'CGAU')
    return dna.translate(trans_table)
