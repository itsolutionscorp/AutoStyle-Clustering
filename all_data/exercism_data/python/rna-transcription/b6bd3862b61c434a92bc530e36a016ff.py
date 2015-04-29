from string import maketrans

translation = maketrans('GCTA', 'CGAU')

def to_rna(string):
    return string.translate(translation)
