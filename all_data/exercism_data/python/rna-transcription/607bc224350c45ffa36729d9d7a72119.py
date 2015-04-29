from string import maketrans, translate

translation_table = maketrans('GCTA', 'CGAU')

def to_rna(strand):
    return translate(strand, translation_table)
