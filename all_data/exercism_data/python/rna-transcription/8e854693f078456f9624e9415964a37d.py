from string import maketrans, translate

d2r_tab = maketrans('GCTA', 'CGAU')

def to_rna(strand):
    return translate(strand, d2r_tab)
