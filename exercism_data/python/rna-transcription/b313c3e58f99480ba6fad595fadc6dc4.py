import string

dnaToRna = string.maketrans('GCTA', 'CGAU')

def to_rna(s):
    "Given a DNA strand, return its RNA complement"
    return s.upper().translate(dnaToRna)
