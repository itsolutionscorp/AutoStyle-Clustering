import string

table = string.maketrans('GCTA', 'CGAU')

def to_rna(s):
    return s.translate(table)
