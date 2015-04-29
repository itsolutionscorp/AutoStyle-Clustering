from string import maketrans
def to_rna(t):
    return t.translate(maketrans('GCTA', 'CGAU'))
