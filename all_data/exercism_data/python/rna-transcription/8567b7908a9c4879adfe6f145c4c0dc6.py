import string

def to_rna(src):
    
    return src.translate(string.maketrans('GCTA', 'CGAU'))
