from string import maketrans

def to_rna(a_string):
    trans = maketrans('GCTA', 'CGAU')
    return a_string.translate(trans)
