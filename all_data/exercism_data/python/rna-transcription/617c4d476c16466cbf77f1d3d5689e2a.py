from string import maketrans

def to_rna(string):
    return string.translate(maketrans('GCTA', 'CGAU'))
